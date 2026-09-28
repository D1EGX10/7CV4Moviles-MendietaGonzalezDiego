package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dmendieta2005.gmail.dmendieta2005.xmltarea2.datos.DatosCompartidos

class entradadetexto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entradadetexto, container, false)

        val opciones = arrayOf("México", "Colombia", "Argentina", "España", "Perú")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, opciones)
        val autoComplete = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteOpciones)
        autoComplete.setAdapter(adapter)

        val inputError = view.findViewById<TextInputEditText>(R.id.inputError)
        val layoutError = view.findViewById<TextInputLayout>(R.id.layoutError)
        val errorMensaje = getString(R.string.error_codigo)

        inputError.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length < 5) {
                    layoutError.error = errorMensaje
                } else {
                    layoutError.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        val inputNombre = view.findViewById<TextInputEditText>(R.id.inputNombre)
        inputNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                DatosCompartidos.establecerNombre(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        val btnAgregar = view.findViewById<MaterialButton>(R.id.btnAgregarLista)
        btnAgregar.setOnClickListener {
            val texto = inputNombre.text?.toString() ?: ""
            val agregado = DatosCompartidos.agregarElemento(texto)
            val mensaje = when {
                agregado -> getString(R.string.mensaje_agregado_lista)
                texto.isBlank() -> getString(R.string.mensaje_nombre_vacio)
                else -> getString(R.string.mensaje_nombre_duplicado)
            }
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}