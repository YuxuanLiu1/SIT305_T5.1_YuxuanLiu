package com.example.task51;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.jar.Attributes;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> Names = new ArrayList<>();
    private ArrayList<String> ImageURLs = new ArrayList<>();
    private ArrayList<String> content = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        ImageURLs.add("https://www.austravel.com/sites/default/files/styles/homepage_image_slider/public/visionsofvictoria1374399-833.jpg?itok=alH5eSPy");
        Names.add("The Great Ocean Road");
        content.add("the Great Ocean Road is undoubtedly one of Australia’s most popular and scenic driving routes.");
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView recyclerView1 = findViewById(R.id.recyclerView1);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,Names,ImageURLs,content);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager1);

//        RecyclerViewAdapter_2 adapter2 = new RecyclerViewAdapter_2(this,ImageURLs);
//        recyclerView1.setAdapter(adapter2);
//        LinearLayoutManager manager2 = new LinearLayoutManager(this);
//        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(manager2);

    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> Names = new ArrayList<>();
        private ArrayList<String> ImageURLs = new ArrayList<>();
        private ArrayList<String> content = new ArrayList<>();
        private Context context;

        public RecyclerViewAdapter(Context context, ArrayList<String> Names, ArrayList<String> ImageURLs, ArrayList<String> content) {
            this.Names = Names;
            this.ImageURLs = ImageURLs;
            this.context = context;
            this.content = content;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cycle1, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context)
                    .asBitmap()
                    .load(ImageURLs.get(position))
                    .into(holder.image);

            holder.content_title.setText(Names.get(position));
            holder.content_overview.setText(content.get(position));
            holder.constraintlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View View) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Names",Names.get(position));
                    bundle.putString("content",content.get(position));
                    bundle.putString("ImageURLs",ImageURLs.get(position));
                    bundle.putString("context",context.toString());

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Fragment fragment1 = new Fragment();
                    fragment1.setArguments(bundle);

                    fragmentTransaction.replace(R.id.container, fragment1);
                    fragmentTransaction.commit();

                }
            });
        }

        @Override
        public int getItemCount() {return ImageURLs.size();}

        public class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView image;
            TextView content_title;
            RelativeLayout constraintlayout;
            TextView content_overview;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                content_title = itemView.findViewById(R.id.content_title);
                constraintlayout = itemView.findViewById(R.id.constraintlayout);
                content_overview = itemView.findViewById(R.id.content_overview);


            }
        }

    }

    public static class RecyclerViewAdapter_2 extends RecyclerView.Adapter<RecyclerViewAdapter_2.ViewHolder> {
        private ArrayList<String> ImageURLs = new ArrayList<>();
        private Context context;

        public  RecyclerViewAdapter_2(Context context, ArrayList<String> ImageURLs) {
            this.ImageURLs = ImageURLs;
            this.context = context;

        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cycle2,parent, false);
            ViewHolder holder = new ViewHolder(view);
            return  holder;
        }

        @Override
        public void  onBindViewHolder(@NonNull ViewHolder holder, int position){
            Glide.with(context)
                    .asBitmap()
                    .load(ImageURLs.get(position))
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount(){return ImageURLs.size();}

    }
}