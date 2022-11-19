package com.octaneocatane.shoppinglist.presentation.screens

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.octaneocatane.shoppinglist.databinding.FragmentShopItemBinding
import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.shoppinglist.app.ShoppingListApp
import com.octaneocatane.shoppinglist.presentation.viewmodel.ShopItemViewModel
import com.octaneocatane.shoppinglist.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class ShopItemFragment: Fragment() {

    private val component by lazy {
        (requireActivity().application as ShoppingListApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory)[ShopItemViewModel::class.java]
    }

    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException(BINDING_NULL_EXCEPTION_MESSAGE)

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemEntityId: Int = ShopItemEntity.UNDEFINED_ID

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this@ShopItemFragment)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException(ACTIVITY_LISTENER_EXCEPTION_MESSAGE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        binding.editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.editCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemEntityId)
        binding.buttonSave.setOnClickListener {
            viewModel.editShopItem(
                binding.editName.text?.toString(),
                binding.editCount.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        binding.buttonSave.setOnClickListener {
            viewModel.addShopItem(
                binding.editName.text?.toString(),
                binding.editCount.text?.toString()
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException(SCREEN_MODE_IS_ABSENT_EXCEPTION_MESSAGE)
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("$UNKNOWN_SCREEN_MODE $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException(PARAM_SHOP_ITEM_ID_IS_ABSENT_EXCEPTION_MESSAGE)
            }
            shopItemEntityId = args.getInt(SHOP_ITEM_ID, ShopItemEntity.UNDEFINED_ID)
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        private const val BINDING_NULL_EXCEPTION_MESSAGE = "FragmentShopItemBinding = null"
        private const val ACTIVITY_LISTENER_EXCEPTION_MESSAGE = "Activity must implement onEditingFinishedListener"
        private const val SCREEN_MODE_IS_ABSENT_EXCEPTION_MESSAGE = "Param screen mode is absent"
        private const val UNKNOWN_SCREEN_MODE = "Unknown screen mode"
        private const val PARAM_SHOP_ITEM_ID_IS_ABSENT_EXCEPTION_MESSAGE = "Param shop item id is absent"

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }
}

