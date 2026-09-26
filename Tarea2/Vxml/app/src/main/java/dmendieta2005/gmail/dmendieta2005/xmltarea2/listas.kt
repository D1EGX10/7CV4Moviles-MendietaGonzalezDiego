package dmendieta2005.gmail.dmendieta2005.xmltarea2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class listas : Fragment() {

    private val itemsLista = mutableListOf<ElementoLista>()
    private lateinit var listaAdapter: ListaAdapter
    private lateinit var layoutVacio: View
    private lateinit var rvListaCompleja: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listas, container, false)

        rvListaCompleja = view.findViewById(R.id.rvListaCompleja)
        layoutVacio = view.findViewById(R.id.layoutVacio)
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)

        generarDatosLista()
        listaAdapter = ListaAdapter(itemsLista) { item ->
            Toast.makeText(requireContext(), getString(R.string.item_clic, item.texto), Toast.LENGTH_SHORT).show()
        }
        rvListaCompleja.layoutManager = LinearLayoutManager(requireContext())
        rvListaCompleja.adapter = listaAdapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (!itemsLista[position].esEncabezado) {
                    itemsLista.removeAt(position)
                    listaAdapter.notifyItemRemoved(position)
                    verificarEstadoVacio()
                    Toast.makeText(requireContext(), getString(R.string.item_eliminado), Toast.LENGTH_SHORT).show()
                } else {
                    listaAdapter.notifyItemChanged(position)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(rvListaCompleja)

        swipeRefresh.setOnRefreshListener {
            generarDatosLista()
            listaAdapter.notifyDataSetChanged()
            verificarEstadoVacio()
            swipeRefresh.isRefreshing = false
        }

        val rvCuadricula = view.findViewById<RecyclerView>(R.id.rvCuadricula)
        rvCuadricula.layoutManager = GridLayoutManager(requireContext(), 3)
        val itemsGrid = (1..12).map { "Item $it" }
        rvCuadricula.adapter = GridAdapter(itemsGrid)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val titulosTabs = listOf("Explorar", "Favoritos", "Ajustes")
        viewPager.adapter = TabAdapter(titulosTabs)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titulosTabs[position]
        }.attach()

        return view
    }

    private fun generarDatosLista() {
        itemsLista.clear()
        itemsLista.add(ElementoLista(true, "Categoría Principal"))
        for (i in 1..7) itemsLista.add(ElementoLista(false, "Elemento $i"))
        itemsLista.add(ElementoLista(true, "Categoría Secundaria"))
        for (i in 8..15) itemsLista.add(ElementoLista(false, "Elemento $i"))
    }

    private fun verificarEstadoVacio() {
        val soloEncabezados = itemsLista.all { it.esEncabezado }
        if (itemsLista.isEmpty() || soloEncabezados) {
            rvListaCompleja.visibility = View.GONE
            layoutVacio.visibility = View.VISIBLE
        } else {
            rvListaCompleja.visibility = View.VISIBLE
            layoutVacio.visibility = View.GONE
        }
    }
}

data class ElementoLista(val esEncabezado: Boolean, val texto: String)

class ListaAdapter(
    private val items: List<ElementoLista>,
    private val onClic: (ElementoLista) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = if (items[position].esEncabezado) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_encabezado, parent, false)
            EncabezadoViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is EncabezadoViewHolder) {
            holder.tvTexto.text = item.texto
        } else if (holder is ItemViewHolder) {
            holder.tvTexto.text = item.texto
            holder.itemView.setOnClickListener { onClic(item) }
        }
    }

    override fun getItemCount(): Int = items.size

    class EncabezadoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTexto: TextView = view.findViewById(R.id.tvEncabezado)
    }
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTexto: TextView = view.findViewById(R.id.tvItemTitulo)
    }
}

class GridAdapter(private val items: List<String>) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cuadricula, parent, false)
        return GridViewHolder(view)
    }
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.tvTexto.text = items[position]
    }
    override fun getItemCount(): Int = items.size
    class GridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTexto: TextView = view.findViewById(R.id.tvGridTitulo)
    }
}

class TabAdapter(private val items: List<String>) : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tab_content, parent, false)
        return TabViewHolder(view)
    }
    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.tvTexto.text = items[position]
    }
    override fun getItemCount(): Int = items.size
    class TabViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTexto: TextView = view.findViewById(R.id.tvTabContent)
    }
}