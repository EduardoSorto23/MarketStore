package com.sorto.marketstore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.sorto.marketstore.Products;
import com.sorto.marketstore.R;
import com.sorto.marketstore.adapters.GridAdapter;
import com.sorto.marketstore.models.Product;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class GridFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Creación prefabricada de un {@link GridFragment}
     */
    public static GridFragment newInstance(int sectionNumber) {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Obtención del grid view
        GridViewWithHeaderAndFooter grid =
                (GridViewWithHeaderAndFooter) rootView.findViewById(R.id.gridview);

        // Inicializar el grid view
        setUpGridView(grid);

        return rootView;
    }

    /**
     * Infla el grid view del fragmento dependiendo de la sección
     *
     * @param grid Instancia del grid view
     */
    private void setUpGridView(GridViewWithHeaderAndFooter grid) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        switch (section_number) {
            case 1:
                grid.addHeaderView(createHeaderView(6, Products.getTelefonos()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getTelefonos()));
                break;
            case 2:
                grid.addHeaderView(createHeaderView(6, Products.getTablets()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getTablets()));
                break;
            case 3:
                grid.addHeaderView(createHeaderView(6, Products.getPortatiles()));
                grid.setAdapter(new GridAdapter(getActivity(), Products.getPortatiles()));
                break;
        }
    }

    /**
     * Crea un view de cabecera para mostrarlo en el principio del grid view.
     *
     * @param position Posición del item que sera el grid view dentro de {@code items}
     * @param items    Array de productos
     * @return Header View
     */
    private View createHeaderView(int position, Product[] items) {

        View view;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.grid_header, null, false);

        Product item = items[position];

        // Seteando Imagen
        ImageView image = (ImageView) view.findViewById(R.id.imagen);
        Glide.with(image.getContext()).load(item.getIdThumbnail()).into(image);

        // Seteando Nombre
        TextView name = (TextView) view.findViewById(R.id.nombre);
        name.setText(item.getNombre());

        // Seteando Descripción
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        descripcion.setText(item.getDescripcion());

        // Seteando Precio
        TextView precio = (TextView) view.findViewById(R.id.precio);
        precio.setText(item.getPrecio());

        // Seteando Rating
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
        ratingBar.setRating(item.getRating());

        return view;
    }

}
