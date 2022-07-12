package com.example.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.navArgs
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.navigationcomponent.R
import org.json.JSONObject

class DataFragment : Fragment() {
    val args : DataFragmentArgs by navArgs()//recuperamos los datos enviados
    //-------------declaramos los componentes a usar-------------------------
    lateinit var text1 : TextView
    lateinit var text2 : TextView
    lateinit var text3 : TextView
    lateinit var text6 : TextView
    lateinit var text7 : TextView
    lateinit var text8 : TextView
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText
    lateinit var textResultado : TextView
    lateinit var layoutResult : LinearLayout
    //-----------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnV : Button = view.findViewById(R.id.buttonV)//boton que realizara la operacion de calcular
        val imgRecibida : ImageView = view.findViewById(R.id.imgRecibida)//imagne que mostrar la figura
        val nombreRecibido : TextView = view.findViewById(R.id.nombreRecibido)
        textResultado = view.findViewById(R.id.textResultado)
        layoutResult = view.findViewById(R.id.layoutResultados)
        imgRecibida.setImageResource(args.img)// cargamos la img que se nos fue enviada desde figuras fragment
        nombreRecibido.text = args.nombre// cargamos el nombre que se nos fue enviada desde figuras fragment
        cargarData(view)//cargamos los datos o la informacion que se mostrar dependiendo de que figura se este tratando
        btnV.setOnClickListener {
            if (validarCampos()){
                layoutResult.visibility = View.VISIBLE
                servicio(enviar())
            }
        }
    }
    fun cargarData(view: View) {
        if (args.figura == 1 ) {// agregamos solo 1 texto ya que asi es necesario
            text1("Longitud de una arista",view)
        } else if (args.figura == 4 ) {// agregamos solo 1 texto ya que asi es necesario
            text1("radio de la esfera",view)
        } else if (args.figura == 3) {// agregamos solo 2 textos ya que asi es necesario
            text1("Radio del cilindro", view)
            text2("Altura del cilindro", view)
        } else if (args.figura == 5) {// agregamos solo 2 textos ya que asi es necesario
            text1("Radio del cono", view)
            text2("Altura del cono", view)
        } else if (args.figura == 2) {// agregamos solo 2 textos ya que asi es necesario
            text1("Altura de la piramide", view)
            text2("Longitud de un lado de la base", view)
        } else if (args.figura == 6) {// agregamos todos los textos ya que asi es necesario
            text1("Longitud del Ortoedro", view)
            text2("Profundidad del Ortoedro", view)
            text3("Altura del Ortoedro",view)
        }//la funcion text 1-2-3 carga los textView y los editText, los hace visible y le indica el texto que deben mostrar
    }
    fun text1(texto : String, view: View){
        text1 = view.findViewById(R.id.textView2)
        text6 = view.findViewById(R.id.textView6)
        editText1 = view.findViewById(R.id.editText1)
        text1.text = texto
        editText1.hint = texto
        text1.visibility = View.VISIBLE
        text6.visibility = View.VISIBLE
        editText1.visibility = View.VISIBLE
    }
    fun text2(texto : String, view: View){
        text2 = view.findViewById(R.id.textView3)
        text7 = view.findViewById(R.id.textView7)
        editText2 = view.findViewById(R.id.editText2)
        text2.text = texto
        editText2.hint = texto
        text2.visibility = View.VISIBLE
        text7.visibility = View.VISIBLE
        editText2.visibility = View.VISIBLE
    }
    fun text3(texto : String, view: View){
        text3 = view.findViewById(R.id.textView4)
        text8 = view.findViewById(R.id.textView8)
        editText3 = view.findViewById(R.id.editText3)
        text3.text = texto
        editText3.hint = texto
        text3.visibility = View.VISIBLE
        text8.visibility = View.VISIBLE
        editText3.visibility = View.VISIBLE
    }
    fun validarCampos():Boolean{//validamos los campos en funcion de la figura que se esta tratando ya que no todas las figuras manejan la misma cantidad de datos
        //devolvemos un boolena de tipo true el cual confirma que no hay cajas vacias en el caso de que no las haya y un false en caso contrario
        if (args.figura == 1 || args.figura == 4) {
            if (editText1.text.toString().equals("")) {
                editText1.setError("Campo vacio")
                return false
            } else {
                return true
            }
        } else if (args.figura == 2 || args.figura == 3 || args.figura == 5) {
            if (editText1.text.toString().equals("")||editText2.text.toString().equals("")) {
                if (editText1.text.toString().equals("")) {
                    editText1.setError("Campo vacio")
                }
                if (editText2.text.toString().equals("")){
                    editText2.setError("Campo vacio")
                }
                return false
            } else {
                return true
            }
        } else if (args.figura == 6) {
            if (editText1.text.toString().equals("")||editText2.text.toString().equals("")||editText3.text.toString().equals("")) {
                if (editText1.text.toString().equals("")) {
                    editText1.setError("Campo vacio")
                }
                if (editText2.text.toString().equals("")) {
                    editText2.setError("Campo vacio")
                }
                if (editText3.text.toString().equals("")) {
                    editText3.setError("Campo vacio")
                }
                return false
            } else {
                return true
            }
        } else {
            return false
        }
    }
    fun enviar():JSONObject{//regresamos un dato de tipo JSON el cual tiene cargada informacion relacionada con la figura que se esta tratando
        var jsonobject = JSONObject()
        jsonobject.put("id",args.figura)//asignamos como id el numero de la figura, es esencial este dato se usan en el servicio, para saber que debe hacer
        if (args.figura == 1 ) {//dependiendo de cada figura agregamos los datos relacionados a cada una de ellas
            jsonobject.put("lado",editText1.text)
        } else if (args.figura == 2 ) {
            jsonobject.put("altura",editText1.text)
            jsonobject.put("lado",editText2.text)
        } else if (args.figura == 3) {
            jsonobject.put("radio",editText1.text)
            jsonobject.put("altura",editText2.text)
        } else if (args.figura == 4) {
            jsonobject.put("radio",editText1.text)
        } else if (args.figura == 5) {
            jsonobject.put("radio",editText1.text)
            jsonobject.put("altura",editText2.text)
        } else if (args.figura == 6) {
            jsonobject.put("longitud",editText1.text)
            jsonobject.put("profundidad",editText2.text)
            jsonobject.put("altura",editText3.text)
        }
        return jsonobject
    }
    fun servicio(jsonobject: JSONObject){//comunicacion con el servidor
        //recibimos un JSON el cual es retornado del metodoo enviar ()
        val queue = Volley.newRequestQueue(context)
        val url = "https://app-figuras-volumen.herokuapp.com/figura_volumen"
        //Toast.makeText(context,jsonobject.toString(),Toast.LENGTH_SHORT).show()
        val jsonObjectRequest = JsonObjectRequest(url, jsonobject,//enviamos la url que ya se definio y el objeto JSON que recibimos
            Response.Listener { response ->
                textResultado.text = response.getString("volumen")//mostramos el resultado en un editText como es un JSON el resultado le indicamos la etiqueta que contiene el resultado
            },
            Response.ErrorListener { error ->
                textResultado.text = error.printStackTrace().toString()//mostramos el msj de error en caso de que haya un error
            }
        )
        queue.add(jsonObjectRequest)
    }
}