package com.example.meugmail.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meugmail.R;
import com.example.meugmail.activity.DetailActivity;
import com.example.meugmail.model.EmailData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {

    private List<EmailData> mEmailData;
    private Context mContext;
    private String favoritado = "nao";

    //A lista a seguir é necessária para trabalhar com exclusão múltipla
    public final SparseBooleanArray selectedItens;
    private int currentSelectedPos;

    //Trabalha em conjunto com a interface MailAdapterListener
    private MailAdapterListener listener;


    public MailAdapter(Context mContext, List<EmailData> mEmailData) {
        this.mEmailData = mEmailData;
        this.mContext = mContext;
        selectedItens = new SparseBooleanArray();
    }

    public void setListener(MailAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public MailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_mail_item,
                parent, false);
        return new MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MailViewHolder holder, final int position) {

        EmailData email = mEmailData.get(position);

        holder.mIcon.setText(mEmailData.get(position).getmSender().substring(0, 1));
        holder.mSender.setText(mEmailData.get(position).getmSender());
        holder.mEmailTitle.setText(mEmailData.get(position).getmTitle());
        holder.mEmailDetails.setText(mEmailData.get(position).getmDetails());
        holder.mEmailTime.setText(mEmailData.get(position).getmTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedItens.size() > 0 && listener != null){
                    listener.onItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(listener != null){
                    listener.onItemLongClick(position);
                }
                return true;
            }
        });

        if(currentSelectedPos == position) { currentSelectedPos = -1;}

//        if(email.isSelected()){
//            holder.mLayout.setBackgroundColor(Color.rgb(227, 242,253));
//            } else holder.mLayout.setBackgroundColor(Color.rgb(255,255,225));

        //Esse código é o responsável pela cor dos ícones redondos, no início de cada mensagem
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256),
                mRandom.nextInt(256));
        ((GradientDrawable) holder.mIcon.getBackground()).setColor(color);

//        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.mFavorite.getColorFilter() != null) {
//                    holder.mFavorite.clearColorFilter();
//                    favoritado = "nao";
//                } else {
//                    holder.mFavorite.setColorFilter(ContextCompat.getColor(mContext,
//                            R.color.colorOrange));
//                    favoritado = "sim";
//                }
//            }
//        });

        //Esse código faz o Relative layout responder ao clique curto do usuário.
        //Para isso, deve-se definir um id (@+id/layout) para o layout no arquivo xml.
//        holder.mLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(mContext, DetailActivity.class);
//
//                mIntent.putExtra("sender", holder.mSender.getText().toString());
//                mIntent.putExtra("title", holder.mEmailTitle.getText().toString());
//                mIntent.putExtra("details", holder.mEmailDetails.getText().toString());
//                mIntent.putExtra("time", holder.mEmailTime.getText().toString());
//                mIntent.putExtra("icon", holder.mIcon.getText().toString());
//                mIntent.putExtra("colorIcon", color);
//
//                if(holder.mFavorite.getColorFilter() != null) {
//                    favoritado = "sim";
//                    mIntent.putExtra("favoritado",favoritado);
//                }else {
//                    favoritado = "nao";
//                    mIntent.putExtra("favoritado", favoritado);
//                }
//
//                mContext.startActivity(mIntent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mEmailData.size();
    }

    public void deleteSelectedMails() {

        List<EmailData> mEmailData = new ArrayList<>();

        for (EmailData email : this.mEmailData) {
            if(email.isSelected())
                mEmailData.add(email);
        }

        this.mEmailData.removeAll(mEmailData);
        notifyDataSetChanged();
        currentSelectedPos = -1;
    }

    public void toggleSelection(int position) {

        currentSelectedPos = position;
        if(selectedItens.get(position)){
            selectedItens.delete(position);
            mEmailData.get(position).setSelected(false);
        }else{
            selectedItens.put(position, true);
            mEmailData.get(position).setSelected(true);
        }

        notifyItemChanged(position);
    }

    class MailViewHolder extends RecyclerView.ViewHolder {

        TextView mIcon, mSender, mEmailTitle, mEmailDetails, mEmailTime;
        ImageView mFavorite;
        RelativeLayout mLayout;

        MailViewHolder(View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.tvIcon);
            mSender = itemView.findViewById(R.id.tvEmailSender);
            mEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
            mEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
            mEmailTime = itemView.findViewById(R.id.tvEmailTime);
            mFavorite = itemView.findViewById(R.id.ivFavorite);
            mLayout = itemView.findViewById(R.id.layout);
        }

    }

    public List getEmails() {
        return mEmailData;
    }

    public interface MailAdapterListener {

        void onItemClick(int position);
        void onItemLongClick(int position);

    }

}





