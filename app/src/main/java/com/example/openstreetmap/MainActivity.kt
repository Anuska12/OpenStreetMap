
package com.example.openstreetmap

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.openstreetmap.databinding.ActivityMainBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the configuration. This should be done before initializing the MapView.
        Configuration.getInstance().load(applicationContext, getPreferences(Context.MODE_PRIVATE))
        binding.map.setTileSource(TileSourceFactory.MAPNIK)
        binding.map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT)
        binding.map.setMultiTouchControls(true)

        val mapController = binding.map.controller
        val zoomLevel = 20.0 // Adjust this as needed

        val center = GeoPoint(23.7582, 90.3896)
        val center1 = GeoPoint(23.75808, 90.39036)
        val center2 = GeoPoint(23.75773, 90.39049)

        mapController.animateTo(center)
        mapController.setZoom(zoomLevel)
        addMarker(center,center1,center2)
    }

    private fun addMarker(center: GeoPoint,center1: GeoPoint,center2: GeoPoint) {
        val marker1 = Marker(binding.map)
        marker1.position = center
        marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        //marker1.icon = resources.getDrawable(R.drawable.custom_marker)


        val marker2 = Marker(binding.map)
        marker2.position = center1
        marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
       // marker2.icon = resources.getDrawable(R.drawable.custom_marker)


        val marker3 = Marker(binding.map)
        marker3.position = center2
        marker3.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        //marker3.icon = resources.getDrawable(R.drawable.custom_marker)

        binding.map.overlays.clear()
        binding.map.overlays.addAll(listOf(marker1,marker2,marker3))
        binding.map.invalidate()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }
}


