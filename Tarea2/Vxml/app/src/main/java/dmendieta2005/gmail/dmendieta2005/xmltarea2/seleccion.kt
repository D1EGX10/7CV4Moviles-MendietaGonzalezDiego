package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.slider.Slider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dmendieta2005.gmail.dmendieta2005.xmltarea2.datos.DatosCompartidos

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

        val switchNotificaciones = view.findViewById<MaterialSwitch>(R.id.switchNotificaciones)
        switchNotificaciones.isChecked = DatosCompartidos.notificacionesActivas.value ?: true
        switchNotificaciones.setOnCheckedChangeListener { _, activo ->
            DatosCompartidos.establecerNotificaciones(activo)
            val mensaje = if (activo) "Notificaciones activadas" else "Notificaciones desactivadas"
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }

        val sliderTamano = view.findViewById<Slider>(R.id.sliderTamano)
        sliderTamano.value = (DatosCompartidos.tamanoContenedor.value ?: 80).toFloat()
        sliderTamano.addOnChangeListener { _, valor, _ ->
            DatosCompartidos.establecerTamano(valor.toInt())
        }

        val chipRojo = view.findViewById<Chip>(R.id.chipRojo)
        val chipAzul = view.findViewById<Chip>(R.id.chipAzul)
        val chipVerde = view.findViewById<Chip>(R.id.chipVerde)
        val chipMorado = view.findViewById<Chip>(R.id.chipMorado)

        chipRojo.setOnClickListener {
            DatosCompartidos.establecerColor(0xFFE53935.toInt())
        }
        chipAzul.setOnClickListener {
            DatosCompartidos.establecerColor(0xFF1E88E5.toInt())
        }
        chipVerde.setOnClickListener {
            DatosCompartidos.establecerColor(0xFF43A047.toInt())
        }
        chipMorado.setOnClickListener {
            DatosCompartidos.establecerColor(0xFF8E24AA.toInt())
        }

        return view
    }
}