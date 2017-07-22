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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PedidosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PedidosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PedidosFragment";
    private ArrayList<Ingrediente> ingredientesArrayList;
    private ArrayList<Plato> platoArrayList;
    private View root;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Spinner spMasPedidos;
    private Spinner spPlatosPersonalizados;
    private Button btnRealizarPedidos;
    private TextView tvPuntos;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ListView listView;
    private PlatosAdapter platosAdapter;
    //private ExpandableListAdapter adapter;

    public PedidosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedidosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PedidosFragment newInstance(String param1, String param2) {
        PedidosFragment fragment = new PedidosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        /*
        platoArrayList = new ArrayList<Plato>();
        platoArrayList.add(crearPlato1());
        platoArrayList.add(crearPlato2());
        platoArrayList.add(crearPlato3());
        platoArrayList.add(crearPlato4());
        platoArrayList.add(crearPlato5());
        platoArrayList.add(crearPlato6());
*/

        String json = FileReaderManager.getInstance(getActivity()).getPlatosGuardados();
        platoArrayList = Parser.getInstance(getActivity()).getPlatosArrayList(json);



/*
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        ingredientes.add(crearIngrediente1());
        ingredientes.add(crearIngrediente2());
        ingredientes.add(crearIngrediente3());
        ingredientes.add(crearIngrediente4());
        ingredientes.add(crearIngrediente5());
        ingredientes.add(crearIngrediente6());
        ingredientes.add(crearIngrediente7());

        ingredientes.remove(0);
*/
    }

    private Plato crearPlato1(){
        Plato plato = new Plato();
        plato.setNombre("1 - Flaco Spineta");
        plato.agregarIngrediente(crearIngrediente1());
        plato.agregarIngrediente(crearIngrediente3());
        plato.agregarIngrediente(crearIngrediente4());

        return plato;
    }

    private Plato crearPlato2(){
        Plato plato = new Plato();
        plato.setNombre("2 - Miles Davis");
        plato.agregarIngrediente(crearIngrediente2());
        plato.agregarIngrediente(crearIngrediente4());
        plato.agregarIngrediente(crearIngrediente5());
        plato.agregarIngrediente(crearIngrediente7());
        return plato;
    }

    private Plato crearPlato3(){
        Plato plato = new Plato();
        plato.setNombre("3 - Maceo Parker");
        plato.agregarIngrediente(crearIngrediente2());
        plato.agregarIngrediente(crearIngrediente3());
        plato.agregarIngrediente(crearIngrediente4());
        plato.agregarIngrediente(crearIngrediente5());

        return plato;
    }
    private Plato crearPlato4(){
        Plato plato = new Plato();
        plato.setNombre("4 - Carlos Santana");
        plato.agregarIngrediente(crearIngrediente3());
        plato.agregarIngrediente(crearIngrediente4());
        plato.agregarIngrediente(crearIngrediente5());
        plato.agregarIngrediente(crearIngrediente6());

        return plato;
    }
    private Plato crearPlato5(){
        Plato plato = new Plato();
        plato.setNombre("5 - Manu Chao");
        plato.agregarIngrediente(crearIngrediente4());
        plato.agregarIngrediente(crearIngrediente5());
        plato.agregarIngrediente(crearIngrediente6());
        return plato;
    }
    private Plato crearPlato6(){
        Plato plato = new Plato();
        plato.setNombre("6 - Herbie Hancock");
        plato.agregarIngrediente(crearIngrediente1());
        plato.agregarIngrediente(crearIngrediente3());
        plato.agregarIngrediente(crearIngrediente5());
        plato.agregarIngrediente(crearIngrediente6());
        return plato;
    }
    private Ingrediente crearIngrediente1(){
        Ingrediente i = new Ingrediente("Pollo", 20);

        return i;
    }

    private Ingrediente crearIngrediente2(){
        Ingrediente i = new Ingrediente("Carne", 25);

        return i;
    }
    private Ingrediente crearIngrediente3(){
        Ingrediente i = new Ingrediente("Queso untable", 15);

        return i;
    }
    private Ingrediente crearIngrediente4(){
        ArrayList<CondicionMedica> condicionesIngrediente = new ArrayList<CondicionMedica>();
        condicionesIngrediente.add(new CondicionMedica(CondicionMedica.CONDICION.DIABETES));
        Ingrediente i = new Ingrediente("Miel", 10, condicionesIngrediente);

        return i;
    }
    private Ingrediente crearIngrediente5(){
        Ingrediente i = new Ingrediente("Jamon Cocido", 15);

        return i;
    }

    private Ingrediente crearIngrediente6(){
        ArrayList<CondicionMedica> condicionesIngrediente = new ArrayList<CondicionMedica>();
        condicionesIngrediente.add(new CondicionMedica(CondicionMedica.CONDICION.HIPERTENSION));
        Ingrediente i = new Ingrediente("Jamon Crudo", 20, condicionesIngrediente);

        return i;
    }

    private Ingrediente crearIngrediente7(){
        ArrayList<CondicionMedica> condicionesIngrediente = new ArrayList<CondicionMedica>();
        condicionesIngrediente.add(new CondicionMedica(CondicionMedica.CONDICION.CELIACO));
        Ingrediente i = new Ingrediente("Harina", 20, condicionesIngrediente);

        return i;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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


        ArrayList<Plato> subList = new ArrayList<Plato>();
        subList.add(platoArrayList.get(0));
        subList.add(platoArrayList.get(1));
        PlatosComboAdapter platosPersonalizadosAdapter = new PlatosComboAdapter(root.getContext(), subList);
        spPlatosPersonalizados.setAdapter(platosPersonalizadosAdapter);
        platosPersonalizadosAdapter.notifyDataSetChanged();

        subList.remove(0);
        subList.remove(0);
        subList.add(platoArrayList.get(2));
        subList.add(platoArrayList.get(3));
        PlatosComboAdapter spMasPedidosAdapter = new PlatosComboAdapter(root.getContext(), subList);
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
