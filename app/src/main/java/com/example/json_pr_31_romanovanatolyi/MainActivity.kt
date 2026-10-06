package com.example.json_pr_31_romanovanatolyi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var product: EditText
    lateinit var  price: EditText
    lateinit var tag: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product = findViewById(R.id.productNameEditText)
        price = findViewById(R.id.productPriceEditText)
    }

    fun SaveObjectBtn(view: View) {}
}