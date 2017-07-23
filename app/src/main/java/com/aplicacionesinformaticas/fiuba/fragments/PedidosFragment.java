package com.aplicacionesinformaticas.fiuba.fragments;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.model.CondicionMedica;
import com.aplicacionesinformaticas.fiuba.model.Ingrediente;
import com.aplicacionesinformaticas.fiuba.model.Orden;
import com.aplicacionesinformaticas.fiuba.model.Plato;
import com.aplicacionesinformaticas.fiuba.model.User;
import com.aplicacionesinformaticas.fiuba.utils.FileReaderManager;
import com.aplicacionesinformaticas.fiuba.utils.Parser;
import com.aplicacionesinformaticas.fiuba.utils.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PedidosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "PedidosFragment";
    private ArrayList<Ingrediente> ingredientesArrayList;
    private ArrayList<Plato> platoArrayList;
    private View root;
    private Spinner spMasPedidos;
    private Spinner spPlatosPersonalizados;
    private Button btnRealizarPedidos;
    private TextView tvPuntos;

    private ListView listView;
    private PlatosAdapter platosAdapter;
    //private ExpandableListAdapter adapter;

    public PedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_pedidos, container, false);
        spMasPedidos = (Spinner)root.findViewById(R.id.spMasPedidos);
        btnRealizarPedidos = (Button) root.findViewById(R.id.btnRealizarPedido);
        tvPuntos = (TextView)root.findViewById(R.id.tvPuntos);

        listView = (ListView) root.findViewById(R.id.lvPedidos);

        inicializarVista();

        return root;
    }

    private void crearPlatos(){
        String jsonPlatos = FileReaderManager.getInstance(getActivity()).getPlatosGuardados();
        platoArrayList = Parser.getInstance(getActivity()).getPlatosArrayList(jsonPlatos);

        String jsonIngredientes = FileReaderManager.getInstance(getActivity()).getIngredientesGuardados();
        ingredientesArrayList = Parser.getInstance(getActivity()).getIngredientesArrayList(jsonIngredientes);
    }

    public class PlatosAdapter extends BaseAdapter {

        private Context context;
        private List<Plato> platos;

        public PlatosAdapter(Context context, List<Plato> platos) {
            this.context = context;
            this.platos = platos;
        }

        public List<Plato> getPlatos(){
            return platos;
        }
        @Override
        public int getCount() {
            return this.platos.size();
        }

        @Override
        public Object getItem(int position) {
            return this.platos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (convertView == null) {
                // Create a new view into the list.
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.list_item, parent, false);
            }

            // Set data into the view.
            //ImageView ivItem = (ImageView) rowView.findViewById(R.id.ivItem);
            TextView nombrePlato = (TextView) rowView.findViewById(R.id.tvNombrePlato);
            final TextView tvCantidad = (TextView) rowView.findViewById(R.id.tvCantidad);
            ImageButton btnAgregar = (ImageButton) rowView.findViewById(R.id.btnAgregar);
            ImageButton btnQuitar  = (ImageButton) rowView.findViewById(R.id.btnQuitar);
            TextView detallePlato = (TextView) rowView.findViewById(R.id.tvDetallePlato);
            tvCantidad.setText(" ");

            final Plato plato = this.platos.get(position);
            Integer precio = (int) plato.getPrecioTotal();
            nombrePlato.setText(plato.getNombre() + " - $ " + String.valueOf(precio));
            String listaIngredientes = "";
            User usuario = User.getUsuarioActual();
            for (int i = 0; i < plato.getIngredientesDelPlato().size(); i++){
                boolean compatible = usuario.esCompatible(plato.getIngredientesDelPlato().get(i));
                if (!compatible){
                    listaIngredientes = listaIngredientes + "<font color=#CC0029>";
                }
                listaIngredientes = listaIngredientes + plato.getIngredientesDelPlato().get(i).getNombre();
                if (!compatible){
                    listaIngredientes = listaIngredientes + "</font>";
                }
                if (i < (plato.getIngredientesDelPlato().size() - 1)){
                    listaIngredientes = listaIngredientes + ", ";
                }
            }

            btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer cantidad = 0;
                    try {
                        cantidad = Integer.valueOf(tvCantidad.getText().toString());
                    } catch (Exception e){
                        cantidad = 0;
                    }
                        cantidad++;
                        plato.setCantidadEnOrden(plato.getCantidadEnOrden() + 1);
                        tvCantidad.setText(String.valueOf(cantidad));


                }
            });
            btnQuitar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer cantidad = 0;
                    try {
                        cantidad = Integer.valueOf(tvCantidad.getText().toString());
                    } catch (Exception e){
                        cantidad = 0;
                    }
                    if (cantidad > 0) {
                        cantidad--;
                        plato.setCantidadEnOrden(plato.getCantidadEnOrden() - 1);
                        tvCantidad.setText(String.valueOf(cantidad));
                    }
                    if (cantidad == 0){
                        tvCantidad.setText(" ");
                    }
                }
            });
            detallePlato.setText(Html.fromHtml(listaIngredientes));
            //ivItem.setImageResource(item.getImage());

            return rowView;
        }

    }

    public class PlatosComboAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Plato> platos;

        public PlatosComboAdapter(Context context, ArrayList<Plato> platosDelCombo) {
            this.context = context;
            this.platos = platosDelCombo;
        }

        @Override
        public int getCount() {
            return this.platos.size();
        }

        @Override
        public Object getItem(int position) {
            return this.platos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (convertView == null) {
                // Create a new view into the list.
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.list_item_ingrediente, parent, false);
            }

            // Set data into the view.
            TextView nombreIngrediente = (TextView) rowView.findViewById(R.id.tvNombreIngrediente);

            Plato plato = (Plato)this.platos.get(position);
            nombreIngrediente.setText(plato.getNombre());

            return rowView;
        }

    }


    public void inicializarVista() {

        //prepareListData();
        crearPlatos();

        platosAdapter = new PlatosAdapter(root.getContext(), platoArrayList);
        listView.setAdapter(platosAdapter);
        platosAdapter.notifyDataSetChanged();

        spPlatosPersonalizados = (Spinner)root.findViewById(R.id.spPlatosPersonalizados);


        ArrayList<Plato> subListMasPedidos = new ArrayList<Plato>();
        ArrayList<Plato> subListSugeridos = new ArrayList<Plato>();
        subListSugeridos.add(platoArrayList.get(0));
        subListSugeridos.add(platoArrayList.get(1));
        PlatosComboAdapter platosPersonalizadosAdapter = new PlatosComboAdapter(root.getContext(), subListSugeridos);
        spPlatosPersonalizados.setAdapter(platosPersonalizadosAdapter);
        platosPersonalizadosAdapter.notifyDataSetChanged();

        subListMasPedidos.add(platoArrayList.get(2));
        subListMasPedidos.add(platoArrayList.get(3));
        PlatosComboAdapter spMasPedidosAdapter = new PlatosComboAdapter(root.getContext(), subListMasPedidos);
        spMasPedidos.setAdapter(spMasPedidosAdapter);
        spMasPedidosAdapter.notifyDataSetChanged();

        btnRealizarPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarPedido();
            }
        });

        tvPuntos.setText("Puntos: " + User.getUsuarioActual().getPuntos());
    }

    private void confirmarPedido(){
        Orden orden = new Orden(null);
        for (int i = 0; i < platosAdapter.getCount(); i++){
            Plato plato = (Plato)platosAdapter.getItem(i);
            for (int j= 0; j < plato.getCantidadEnOrden(); j++){
                orden.agregarPlato(plato.duplicar());
            }

        }

        if ( User.getUsuarioActual().getPuntos() > orden.getCuenta()){
            confirmarRecompensa(orden);
        } else {
            Toast.makeText(getActivity(), "Pedido Realizado", Toast.LENGTH_LONG).show();
            User.getUsuarioActual().getOrdenes().add(orden);
            SharedPreferencesManager.getInstance(getActivity()).saveUser(User.getUsuarioActual());

            inicializarVista();
        }
    }

    public void mostrarResultado(Orden orden){
        Toast.makeText(getActivity(), "Pedido Realizado", Toast.LENGTH_LONG).show();
        User.getUsuarioActual().getOrdenes().add(orden);
        SharedPreferencesManager.getInstance(getActivity()).saveUser(User.getUsuarioActual());

        inicializarVista();
    }

    public void resetearDatasets(){
        platosAdapter.getPlatos().removeAll(platosAdapter.getPlatos());
        platosAdapter.getPlatos().addAll(platoArrayList);
        platosAdapter.notifyDataSetChanged();
    }

    public void confirmarRecompensa(final Orden orden) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Recompensa");
        alert.setMessage("Desea pagar usando sus puntos?");
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                User.getUsuarioActual().agregarPuntosCobrados(orden.getCuenta());
                mostrarResultado(orden);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mostrarResultado(orden);
            }
        });

        alert.show();
    }
}
