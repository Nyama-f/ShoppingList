package com.example.shoppinglist.presentation


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShopItemBinding
import com.example.shoppinglist.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment: Fragment() {

    private lateinit var onShopItemEditingFinishedListener: OnShopItemEditingFinishedListener

    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = checkNotNull(_binding) { "Binding is null" }

    //View model
    private lateinit var viewModel: ShopItemViewModel


    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onAttach(context: Context) {
        Log.d("ShopItemFragment", "onAttach")
        super.onAttach(context)
        if(context is OnShopItemEditingFinishedListener){
            onShopItemEditingFinishedListener = context
        }else{
            throw RuntimeException("Activity must implement OnShopItemEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ShopItemFragment", "onCreate")
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
        Log.d("ShopItemFragment", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    override fun onStart() {
        Log.d("ShopItemFragment", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("ShopItemFragment", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("ShopItemFragment", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("ShopItemFragment", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("ShopItemFragment", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("ShopItemFragment", "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("ShopItemFragment", "onDetach")
        super.onDetach()
    }

    interface OnShopItemEditingFinishedListener {

        fun onShopItemEditingFinished()
    }

    private fun observeViewModel() {
        viewModel.allowClosedScreen.observe(viewLifecycleOwner) {
            onShopItemEditingFinishedListener.onShopItemEditingFinished()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        // ???????? ?????? ?????????????? ?????????? ???? ???? ???????????? ????????????
        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.editTextCount.addTextChangedListener(object : TextWatcher {
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
        viewModel.getShopItem(shopItemId)
        binding.saveButton.setOnClickListener {
            viewModel.editShopItem(binding.editTextName.text?.toString(), binding.editTextCount.text?.toString())
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addShopItem(binding.editTextName.text?.toString(), binding.editTextCount.text?.toString())
        }
    }

    private fun parseParams() {
        val args = arguments ?: throw RuntimeException("Required arguments is absent")
        if (!args.containsKey(KEY_SCREEN_MODE)) {
            throw RuntimeException("Attribute screen mode is absent")
        }
        val mode = args.getString(KEY_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $screenMode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT && !args.containsKey(KEY_SHOP_ITEM_ID)) {
            throw RuntimeException("Param shop item id is absent")
        }
        shopItemId = args.getInt(KEY_SHOP_ITEM_ID)
    }

    companion object {
        private const val KEY_SCREEN_MODE = "screen_mode"
        private const val KEY_SHOP_ITEM_ID = "shop_item_id"
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            val args = Bundle().apply {
                putString(KEY_SCREEN_MODE, MODE_ADD)
            }
            return ShopItemFragment().apply {
                arguments = args
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            val args = Bundle().apply {
                putString(KEY_SCREEN_MODE, MODE_EDIT)
                putInt(KEY_SHOP_ITEM_ID, shopItemId)
            }
            return ShopItemFragment().apply {
                arguments = args
            }
        }
    }
}