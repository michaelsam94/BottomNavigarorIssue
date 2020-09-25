package com.michael.sample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.michael.sample.R
import com.michael.sample.ui.dashboard.DashboardFragment
import com.michael.sample.ui.home.HomeFragment
import com.michael.sample.ui.notifications.NotificationsFragment
import com.pandora.bottomnavigator.BottomNavigator
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var navigator: BottomNavigator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = BottomNavigator.onCreate(
            fragmentContainer = R.id.navHostFragment,
            bottomNavigationView = navView,
            rootFragmentsFactory = mapOf(
                R.id.navigation_home to { HomeFragment() },
                R.id.navigation_dashboard to { DashboardFragment() },
                R.id.navigation_notifications to { NotificationsFragment() }
            ),
            defaultTab = R.id.navigation_home,
            activity = requireActivity()
        )

        requireActivity().onBackPressedDispatcher.addCallback {
            if (!navigator.pop()) {
                findNavController().popBackStack()
            }
        }
    }
}