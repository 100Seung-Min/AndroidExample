package com.example.mapexample

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mapexample.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding
    private lateinit var currentLatLng: LatLng
    private var currentNum = 0
    private lateinit var currentMarker: MarkerOptions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.mapFragment = this
        binding.map.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.map.onLowMemory()
    }

    fun myLocation(view: View) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLng))
    }

    fun moveLocation(view: View) {
        val location = getPosition(requireContext(), binding.address.text.toString())
        if (location == null) {
            Toast.makeText(context, "장소를 찾을 수 없습니다", Toast.LENGTH_SHORT).show()
        } else {
            mMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng(location[currentNum].latitude, location[currentNum].longitude)))
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        Log.d("안녕", "onMapReady: ${getPosition(requireContext(), "영암읍 남문로 38")}")
        mMap.apply {
            currentLatLng = when {
                checkPermissions(requireContext()) -> {
                    LatLng(getLocation(activity)?.latitude!!, getLocation(activity)?.longitude!!)
                }
                else -> {
                    Toast.makeText(context, "위치 권한을 허가해주세요", Toast.LENGTH_SHORT).show()
                    LatLng(37.5662952, 126.97794509999994)
                }
            }
            currentMarker = MarkerOptions().apply {
                title(getAddress(requireContext(), currentLatLng.latitude, currentLatLng.longitude))
                position(currentLatLng)
            }
            addMarker(currentMarker)
            setMinZoomPreference(13f)
            setMaxZoomPreference(17f)
            moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
        }
    }
}