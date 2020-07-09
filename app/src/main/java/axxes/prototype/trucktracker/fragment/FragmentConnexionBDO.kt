package axxes.prototype.trucktracker.fragment

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import axxes.prototype.trucktracker.R
import kotlinx.android.synthetic.main.fragment_connection_bdo.*

class FragmentConnexionBDO: Fragment(), FragmentDevicesScan.ListenerFragmentDevicesScan {

    var listener: ListenerFragmentConnexionBDO? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connection_bdo, container, false)

        val btnConnexion = view.findViewById<Button>(R.id.fcb_btn_connexion_auto)
        btnConnexion.setOnClickListener {

        }

        val btnScan = view.findViewById<Button>(R.id.fcb_btn_scan)
        btnScan.setOnClickListener {
            val dialogFragmentScanDevice = FragmentDevicesScan()
            dialogFragmentScanDevice.setTargetFragment(this,1)
            fragmentManager?.let { it1 -> dialogFragmentScanDevice.show(it1, "Simple dialog fragment") }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ListenerFragmentConnexionBDO
        if (listener == null) {
            throw ClassCastException("$context must implement ListenerFragmentConnexionBDO")
        }

    }

    interface ListenerFragmentConnexionBDO {
        fun onDeviceSelected(device: BluetoothDevice)
    }

    override fun onDeviceSelected(device: BluetoothDevice) {
        fcb_tv_name_bdo.text = device.name
        listener?.onDeviceSelected(device)
    }

}