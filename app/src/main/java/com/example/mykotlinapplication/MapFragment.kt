package com.example.mykotlinapplication

import androidx.fragment.app.Fragment
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mykotlinapplication.databinding.FragmentMapBinding

// Implement OnMapReadyCallback.
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      val binding = DataBindingUtil.inflate<FragmentMapBinding>(inflater,
            R.layout.fragment_map,container,false)
        // Set the layout file as the content view.
        setContentView(R.layout.activity_main);

        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }
}
// val mapFragment = SupportMapFragment.newInstance()
// supportFragmentManager
//     .beginTransaction()
//     .add(R.id.my_container, mapFragment)
//     .commit()
