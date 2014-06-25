package br.ufg.inf.es.sinoa.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufg.inf.es.sinoa.R;
import br.ufg.inf.es.sinoa.vo.Remetente;

public class AdapterRemetentes extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Remetente> itens;

	public AdapterRemetentes(Context context, List<Remetente> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Remetente getItem(int position) {
		return itens.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Remetente remetente = itens.get(position);
		view = mInflater.inflate(R.layout.item_lista_remetentes, null);

		TextView textViewRemetente = (TextView) view.findViewById(R.id.textViewRemetenteLista);
		textViewRemetente.setText(remetente.getNome());
		
		if (remetente.getAtivo().equals(Remetente.ATIVADO)) {
			textViewRemetente.setTypeface(null, Typeface.BOLD);
			((ImageView) view.findViewById(R.id.imageViewSino)).setImageResource(R.drawable.sinoa_sino_remetente);
		} else {
			textViewRemetente.setTextColor(Color.GRAY);
			((ImageView) view.findViewById(R.id.imageViewSino)).setImageResource(R.drawable.blank);
		}
		
		return view;
	}

}
