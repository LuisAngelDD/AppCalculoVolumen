package com.example.navigationcomponent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.adapter.FiguraAdapter
import com.example.calculovolumen.models.Figura
import com.example.navigationcomponent.R

class FigurasFragment : Fragment() {
    lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_figuras, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {//despues de que se creo el fragment
        super.onViewCreated(view, savedInstanceState)
        val navController : NavController = Navigation.findNavController(view)// neceario para acceder al fragment data
        recycler = view.findViewById(R.id.recyclerFiguras)
        val linearLayout = LinearLayoutManager(context)
        recycler.layoutManager = linearLayout
        val figuras = listOf(//declaramos un array el cual contendra las figuras que mostraremos
            Figura(R.drawable.cube,"Cubo",1),
            Figura(R.drawable.pyramid,"Piramide",2),
            Figura(R.drawable.cylinder,"Cilindro",3),
            Figura(R.drawable.esphere,"Esfera",4),
            Figura(R.drawable.cono,"Cono",5),
            Figura(R.drawable.ortoedro,"Ortoedro",6)
        )
        val adapter = FiguraAdapter(figuras,navController)
        recycler.adapter = adapter// mostramos el contenido
    }
}