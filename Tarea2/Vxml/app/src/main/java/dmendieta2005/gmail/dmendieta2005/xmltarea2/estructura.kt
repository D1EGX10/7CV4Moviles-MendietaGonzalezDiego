package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ContenedoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        contenedor: ViewGroup?,
        estado: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estructura, contenedor, false)
    }

    override fun onViewCreated(vista: View, estado: Bundle?) {
        super.onViewCreated(vista, estado)

        val botonAyuda = vista.findViewById<ImageButton>(R.id.barra_ayuda)
        val botonAjustes = vista.findViewById<ImageButton>(R.id.barra_ajustes)
        val botonCompartir = vista.findViewById<ImageButton>(R.id.barra_compartir)

        botonAyuda.setOnClickListener {
            Toast.makeText(requireContext(), "Ayuda", Toast.LENGTH_SHORT).show()
        }

        botonAjustes.setOnClickListener {
            Toast.makeText(requireContext(), "Ajustes", Toast.LENGTH_SHORT).show()
        }

        botonCompartir.setOnClickListener {
            Toast.makeText(requireContext(), "Compartir", Toast.LENGTH_SHORT).show()
        }

        val listaDesplazable = vista.findViewById<LinearLayout>(R.id.lista_desplazable)

        for (numero in 1..25) {
            val elemento = TextView(requireContext())
            elemento.text = getString(R.string.sec6_scroll_elemento, numero)
            elemento.textSize = 14f
            elemento.setPadding(16, 24, 16, 24)

            val separador = View(requireContext())
            val parametros = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2
            )
            separador.layoutParams = parametros
            separador.setBackgroundColor(0x22000000)

            listaDesplazable.addView(elemento)
            listaDesplazable.addView(separador)
        }
    }
}