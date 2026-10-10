package com.example.json_pr_31_romanovanatolyi

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlin.collections.forEach


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

        val productObject : Product = Product("мармелад", 200.0,mutableListOf("сладости","вкусности"))
        val productJSON : String = Gson().toJson(productObject)

        premadeProductName = findViewById(R.id.premadeName)
        premadeProductPrice = findViewById(R.id.premadePrice)
        premadeProductTags = findViewById(R.id.premadeTags)

        premadeProductName.text = Gson().fromJson(productJSON, Product::class.java).name
        premadeProductPrice.text = Gson().fromJson(productJSON, Product::class.java).price.toString()
        val list : List<String> = Gson().fromJson(productJSON, Product::class.java).tags

        var string = ""
        list.forEach { string += "$it, " }

        premadeProductTags.text = string



        product = findViewById(R.id.productNameEditText)
        price = findViewById(R.id.productPriceEditText)
        tag = findViewById(R.id.productTagsEditText)
    }

    fun SaveObjectBtn(view: View)
    {
        var list : MutableList<String> = mutableListOf()
        var count : Int = 0
        var bucket: String = ""


            tag.text.split(',').forEach {

                list.add(it);
            }






            for (i in 0..price.length() - 1)
            {
                if (price.text.toString().toDoubleOrNull() == null)
                {
                    val toast = Toast.makeText(this, "В поле с ценой лишние символы", Toast.LENGTH_SHORT)
                    toast.show()
                    return
                }
            }

        if (tag.length() == 0)
        {
            val toast = Toast.makeText(this, "В поле с тегами пустое", Toast.LENGTH_SHORT)
            toast.show()
            return
        }


        if (product.text.isNotBlank())
        {
            val productObject : Product = Product(product.text.toString(), price.text.toString().toDouble(),list)
            val productJSON : String? = Gson().toJson(productObject)

            premadeProductName.text = Gson().fromJson(productJSON, Product::class.java).name
            premadeProductPrice.text = Gson().fromJson(productJSON, Product::class.java).price.toString()

            var string = ""

            list.forEach { string += "$it, " }

            premadeProductTags.text = string
        }



        else
        {
            val toast = Toast.makeText(this, "Введите название продукта", Toast.LENGTH_SHORT)
            toast.show()
        }


    }
}