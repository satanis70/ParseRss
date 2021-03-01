package ermilov.parserss.InputAdress.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ermilov.parserss.R
import kotlinx.android.synthetic.main.fragment_input_adress.*


class InputAdressFragment : Fragment() {
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_adress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)

        buttonInputAdress.setOnClickListener {
            val sharedPrefAdress = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener
            if (edit_text_adress.text.isNotEmpty()) {
            with (sharedPrefAdress.edit()) {
                    putString("adress", edit_text_adress.text.toString())
                    apply()
                }
                navController.navigate(R.id.action_inputAdressFragment_to_newsFragment)
            } else {
                Toast.makeText(requireContext(), R.string.toast_empty_input, Toast.LENGTH_SHORT).show()
            }
        }

    }


}