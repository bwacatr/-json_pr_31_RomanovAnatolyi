package com.example.json_pr_31_romanovanatolyi

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    lateinit var product: EditText
    lateinit var  price: EditText
    lateinit var tag: EditText

    lateinit var premadeProductName : TextView
    lateinit var premadeProductPrice : TextView
    lateinit var premadeProductTags : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productObject : Product = Product("мармелад", 200.0,listOf("сладости, вкусности"))
        val productJSON : String? = Gson().toJson(productObject)

        premadeProductName = findViewById(R.id.premadeName)
        premadeProductPrice = findViewById(R.id.premadePrice)
        premadeProductTags = findViewById(R.id.premadeTags)



        product = findViewById(R.id.productNameEditText)
        price = findViewById(R.id.productPriceEditText)
        tag = findViewById(R.id.productTagsEditText)
    }

    fun SaveObjectBtn(view: View)
    {
        var list : MutableList<String> = mutableListOf()
        var count : Int = 0
        var bucket: String = ""
        for (i in 0..tag.length() - 1)
        {

            if  (tag.text[i] == ',')
            {
                list.add(bucket)
                count++;
                bucket = ""
            }
            else
            {
                bucket += tag.text[i]
            }
        }

        try {
            for (i in 0..price.length() - 1)
            {
                if (price.text.toString().toDoubleOrNull() == null)
                {
                    throw IllegalArgumentException("В поле с ценой присутствуют символы, кроме чисел или оно пустое")
                }
            }
        }
        catch (e : Exception)
        {
            val toast = Toast.makeText(this, e.cause.toString(), Toast.LENGTH_SHORT)
            toast.show()

        }

        if (product.text.isNotBlank())
        {
        val productObject : Product = Product(product.text.toString(), price.text.toString().toDouble(),list)
        val productJSON : String? = Gson().toJson(productObject)}
        else
        {
            val toast = Toast.makeText(this, "Введите название продукта", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}