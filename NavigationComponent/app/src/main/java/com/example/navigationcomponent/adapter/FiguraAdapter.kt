package com.example.navigationcomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.calculovolumen.models.Figura
import com.example.navigationcomponent.R
import com.example.navigationcomponent.fragments.FigurasFragmentDirections

class FiguraAdapter (val figuras : List<Figura>,val navController : NavController):RecyclerView.Adapter<FiguraAdapter.FiguraViewHolder>(){

    inner class FiguraViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val imagen: ImageView = itemView.findViewById(R.id.img)
        val titulo: TextView  = itemView.findViewById(R.id.nombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiguraViewHolder {//indicamos de daonde se van a mostrar los datos
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return FiguraViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiguraViewHolder, position: Int) {
        val item = figuras[position]
        //indicamos los datos que se motraran en cada card
        holder.imagen.setImageResource(item.imagen)
        holder.titulo.text = item.nombre
        //------------------------------------------------
        holder.itemView.setOnClickListener {
            //enviamos los datos a traves del componente de navegacion las variables se declaraon en el xml nav_graph en navitagion
            val action : NavDirections = FigurasFragmentDirections.actionFigurasFragmentToDataFragment(item.nombre,item.imagen,item.figura)
            navController.navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return figuras.size
    }
}