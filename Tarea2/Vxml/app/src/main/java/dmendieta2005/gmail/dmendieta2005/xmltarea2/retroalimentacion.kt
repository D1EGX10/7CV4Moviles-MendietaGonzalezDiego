package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dmendieta2005.gmail.dmendieta2005.xmltarea2.datos.DatosCompartidos

class InformacionFragment : Fragment() {

    private lateinit var progresoLinealDet: ProgressBar
    private lateinit var progresoCircularDet: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_retroalimentacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progresoLinealDet = view.findViewById(R.id.progreso_lineal_det)
        progresoCircularDet = view.findViewById(R.id.progreso_circular_det)

        val imgUrl = view.findViewById<ImageView>(R.id.img_url)
        val btnSimular = view.findViewById<Button>(R.id.btn_simular_progreso)
        val btnToast = view.findViewById<Button>(R.id.btn_toast)
        val btnSnackbar = view.findViewById<Button>(R.id.btn_snackbar)
        val btnDialogo = view.findViewById<Button>(R.id.btn_dialogo)
        val btnBottomSheet = view.findViewById<Button>(R.id.btn_bottom_sheet)

        val txtNombre = view.findViewById<TextView>(R.id.txtNombreCompartido)
        val txtElemento = view.findViewById<TextView>(R.id.txtElementoCompartido)
        val txtProgreso = view.findViewById<TextView>(R.id.txtProgresoCompartido)
        val txtNotificaciones = view.findViewById<TextView>(R.id.txtNotificacionesCompartido)

        Glide.with(this)
            .load("https://picsum.photos/800/400")
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_delete)
            .into(imgUrl)

        DatosCompartidos.nombreUsuario.observe(viewLifecycleOwner) { nombre ->
            val texto = if (nombre.isNullOrBlank()) getString(R.string.sec5_sin_definir) else nombre
            txtNombre.text = "${getString(R.string.sec5_nombre_label)} $texto"
        }

        DatosCompartidos.elementoSeleccionado.observe(viewLifecycleOwner) { elemento ->
            val texto = if (elemento.isNullOrBlank()) getString(R.string.sec5_ninguno) else elemento
            txtElemento.text = "${getString(R.string.sec5_elemento_label)} $texto"
        }

        DatosCompartidos.progresoGlobal.observe(viewLifecycleOwner) { progreso ->
            txtProgreso.text = "${getString(R.string.sec5_progreso_label)} $progreso%"
            progresoLinealDet.progress = progreso.coerceAtMost(100)
            progresoCircularDet.progress = progreso.coerceAtMost(100)
        }

        DatosCompartidos.notificacionesActivas.observe(viewLifecycleOwner) { activas ->
            val texto = if (activas) getString(R.string.sec5_activadas) else getString(R.string.sec5_desactivadas)
            txtNotificaciones.text = "${getString(R.string.sec5_notificaciones_label)} $texto"
        }

        btnSimular.setOnClickListener {
            DatosCompartidos.avanzarProgreso()
            Toast.makeText(
                requireContext(),
                "Progreso: ${DatosCompartidos.progresoGlobal.value}%",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnToast.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.sec5_toast_texto),
                Toast.LENGTH_SHORT
            ).show()
        }

        btnSnackbar.setOnClickListener {
            Snackbar.make(it, R.string.sec5_snackbar_texto, Snackbar.LENGTH_LONG)
                .setAction(R.string.sec5_snackbar_accion) {
                    Toast.makeText(
                        requireContext(),
                        "Acción deshecha",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }

        btnDialogo.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.sec5_dialogo_titulo)
                .setMessage(R.string.sec5_dialogo_mensaje)
                .setNegativeButton(R.string.sec5_dialogo_cancelar) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(R.string.sec5_dialogo_confirmar) { _, _ ->
                    Toast.makeText(
                        requireContext(),
                        "Acción confirmada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .show()
        }

        btnBottomSheet.setOnClickListener {
            val sheetDialog = BottomSheetDialog(requireContext())
            val sheetView = layoutInflater.inflate(
                R.layout.sheet_bottom_informacion, null
            )
            sheetDialog.setContentView(sheetView)

            sheetView.findViewById<View>(R.id.sheet_opcion1).setOnClickListener {
                Toast.makeText(requireContext(), "Compartir", Toast.LENGTH_SHORT).show()
                sheetDialog.dismiss()
            }
            sheetView.findViewById<View>(R.id.sheet_opcion2).setOnClickListener {
                Toast.makeText(requireContext(), "Guardado en favoritos", Toast.LENGTH_SHORT).show()
                sheetDialog.dismiss()
            }
            sheetView.findViewById<View>(R.id.sheet_opcion3).setOnClickListener {
                Toast.makeText(requireContext(), "Reportado", Toast.LENGTH_SHORT).show()
                sheetDialog.dismiss()
            }

            sheetDialog.show()
        }
    }
}