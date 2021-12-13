package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class listExpAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> listaCampos;
    private Map<String, ArrayList<String>> dato;
    private Context context;
    registrar Registrar;
    Button registro;

    public listExpAdapter(ArrayList<String> listaCampos, Map<String, ArrayList<String>> dato, Context context) {
        this.listaCampos = listaCampos;
        this.dato = dato;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listaCampos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dato.get(listaCampos.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaCampos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dato.get(listaCampos.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String tituloCampo = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.campos, null);
        TextView camposView = convertView.findViewById(R.id.campos);
        camposView.setText(tituloCampo);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String item = (String) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.datos, null);
        EditText datoView = convertView.findViewById(R.id.datos);
        datoView.setHint(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
