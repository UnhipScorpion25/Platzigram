 package com.platzi.platzigram.view.fragment;


 import android.os.Bundle;
 import android.support.v4.app.Fragment;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.support.v7.widget.Toolbar;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import com.platzi.platzigram.R;
 import com.platzi.platzigram.adapter.PictureAdapterRecyclerView;
 import com.platzi.platzigram.model.Picture;

 import java.util.ArrayList;

 /**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, view);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(), R.layout.cartview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

     public ArrayList<Picture> buidPictures(){
         ArrayList<Picture> pictures= new ArrayList<>();
         pictures.add(new Picture("http://comunidad.iebschool.com/desgranandoelvideojuego/files/2015/11/smite-1.png", "Uriel Ramirez", "4 dias", "3 Me Gusta"));
         pictures.add(new Picture("http://www.gamingesports.com/wp-content/uploads/2016/03/Smite-PlayStation-4-GeS.jpg", "Juan Pablo", "3 dias", "10 Me Gusta"));
         pictures.add(new Picture("https://img.youtube.com/vi/nFbEnF7SO14/hqdefault.jpg", "Anahi Salgado", "2 dias", "9 Me Gusta"));
         return pictures;
     }

    public void showToolbar(String title,boolean upBotton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upBotton);
    }

}
