package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

        return view
    }
}