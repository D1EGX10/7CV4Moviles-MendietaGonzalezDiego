package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class seleccion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seleccion, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinnerSeleccion)
        val opcionesSpinner = arrayOf("Opción 1", "Opción 2", "Opción 3")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, opcionesSpinner)
        spinner.adapter = adapter

        val btnFecha = view.findViewById<MaterialButton>(R.id.btnFecha)
        val btnHora = view.findViewById<MaterialButton>(R.id.btnHora)

        btnFecha.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.btn_fecha))
                .build()
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        btnHora.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(getString(R.string.btn_hora))
                .build()
            timePicker.show(parentFragmentManager, "TIME_PICKER")
        }

        return view
    }
}