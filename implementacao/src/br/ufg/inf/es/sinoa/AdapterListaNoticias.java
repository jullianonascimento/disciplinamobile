package br.ufg.inf.es.sinoa;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListaNoticias extends BaseAdapter {
	
	private LayoutInflater mInflater;
    private List<Notificacao> itens;
    
    public AdapterListaNoticias(Context context, List<Notificacao> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

	@Override
	public int getCount() {
		 return itens.size();
	}

	@Override
	public Notificacao getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Notificacao notificacao = itens.get(position);
		view = mInflater.inflate(R.layout.item_lista_notificacoes, null);
		
		((TextView) view.findViewById(R.id.textViewTituloItem)).setText(notificacao.getTitulo());
		
		if (notificacao.getStatus().equals("false")){
			((TextView) view.findViewById(R.id.textViewTituloItem)).setTypeface(null, Typeface.BOLD);
			((ImageView) view.findViewById(R.id.imageViewStatus)).setImageResource(R.drawable.envelope_fechado);
		} 
		
		((TextView) view.findViewById(R.id.textViewRemetenteItem)).setText(notificacao.getRemetente());
		((TextView) view.findViewById(R.id.textViewDataItem)).setText(notificacao.getData());
		
		return view;
	}

}
