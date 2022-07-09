package com.example.shoppinglist.presentation

import androidx.databinding.BindingAdapter
import com.example.shoppinglist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputName: TextInputLayout, isError: Boolean){
    val message = if (isError) {
        textInputName.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputName.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputCount: TextInputLayout, isError: Boolean){
    val message = if (isError) {
        textInputCount.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputCount.error = message
}