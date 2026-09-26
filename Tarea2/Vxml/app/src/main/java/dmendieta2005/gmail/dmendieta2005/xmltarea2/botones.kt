package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class botones : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_botones, container, false)

        val btnRelleno = view.findViewById<MaterialButton>(R.id.btnRelleno)
        val btnContorno = view.findViewById<MaterialButton>(R.id.btnContorno)
        val btnTexto = view.findViewById<MaterialButton>(R.id.btnTexto)
        val btnSoloIcono = view.findViewById<MaterialButton>(R.id.btnSoloIcono)
        val btnIconoTexto = view.findViewById<MaterialButton>(R.id.btnIconoTexto)
        val fabNormal = view.findViewById<FloatingActionButton>(R.id.fabNormal)
        val fabExtendido = view.findViewById<ExtendedFloatingActionButton>(R.id.fabExtendido)
        val btnCarga = view.findViewById<MaterialButton>(R.id.btnCarga)

        val mensajeBase = getString(R.string.mensaje_toast_boton)

        val clickListener = View.OnClickListener { v ->
            val nombreBoton = when (v.id) {
                R.id.btnRelleno -> getString(R.string.btn_relleno)
                R.id.btnContorno -> getString(R.string.btn_contorno)
                R.id.btnTexto -> getString(R.string.btn_texto)
                R.id.btnSoloIcono -> "Solo ícono"
                R.id.btnIconoTexto -> getString(R.string.btn_icono_texto)
                R.id.fabNormal -> "FAB Normal"
                R.id.fabExtendido -> getString(R.string.btn_fab_extendido)
                else -> "Botón"
            }
            Toast.makeText(requireContext(), String.format(mensajeBase, nombreBoton), Toast.LENGTH_SHORT).show()
        }

        btnRelleno.setOnClickListener(clickListener)
        btnContorno.setOnClickListener(clickListener)
        btnTexto.setOnClickListener(clickListener)
        btnSoloIcono.setOnClickListener(clickListener)
        btnIconoTexto.setOnClickListener(clickListener)
        fabNormal.setOnClickListener(clickListener)
        fabExtendido.setOnClickListener(clickListener)

        btnCarga.setOnClickListener {
            btnCarga.text = getString(R.string.btn_cargando)
            btnCarga.isEnabled = false
            Toast.makeText(requireContext(), String.format(mensajeBase, getString(R.string.btn_carga)), Toast.LENGTH_SHORT).show()

            view.postDelayed({
                btnCarga.text = getString(R.string.btn_carga)
                btnCarga.isEnabled = true
            }, 2000)
        }

        return view
    }
}