package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class InformacionFragment : Fragment() {

    private lateinit var progresoLinealDet: ProgressBar
    private lateinit var progresoCircularDet: ProgressBar
    private var progresoActual = 30

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_retroalimentacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ---- Referencias ----
        progresoLinealDet = view.findViewById(R.id.progreso_lineal_det)
        progresoCircularDet = view.findViewById(R.id.progreso_circular_det)

        val imgUrl = view.findViewById<ImageView>(R.id.img_url)
        val btnSimular = view.findViewById<Button>(R.id.btn_simular_progreso)
        val btnToast = view.findViewById<Button>(R.id.btn_toast)
        val btnSnackbar = view.findViewById<Button>(R.id.btn_snackbar)
        val btnDialogo = view.findViewById<Button>(R.id.btn_dialogo)
        val btnBottomSheet = view.findViewById<Button>(R.id.btn_bottom_sheet)

        // ---- Imagen desde URL con Glide ----
        Glide.with(this)
            .load("https://picsum.photos/800/400")
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_delete)
            .into(imgUrl)

        // ---- Simulación de progreso ----
        btnSimular.setOnClickListener {
            progresoActual = (progresoActual + 15) % 120
            progresoLinealDet.progress = progresoActual.coerceAtMost(100)
            progresoCircularDet.progress = progresoActual.coerceAtMost(100)
            Toast.makeText(
                requireContext(),
                "Progreso: ${progresoActual.coerceAtMost(100)}%",
                Toast.LENGTH_SHORT
            ).show()
        }

        // ---- Toast ----
        btnToast.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.sec5_toast_texto),
                Toast.LENGTH_SHORT
            ).show()
        }

        // ---- Snackbar con acción ----
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

        // ---- Diálogo de confirmación ----
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

        // ---- Bottom sheet ----
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