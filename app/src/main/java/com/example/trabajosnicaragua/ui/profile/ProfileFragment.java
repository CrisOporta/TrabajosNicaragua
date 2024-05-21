package com.example.trabajosnicaragua.ui.profile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajosnicaragua.R;
import com.example.trabajosnicaragua.databinding.FragmentProfileBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.DBHelper;
import data.SharedViewModel;
import es.dmoral.toasty.Toasty;
import models.Usuario;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private SharedViewModel sharedViewModel;

    private Usuario usuario;
    private int userId;
    private String userRol;
    DBHelper dbHelper = new DBHelper();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel homeViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);


        // Retrieve the ViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Now you can use the user_rol and user_id
        userId = sharedViewModel.getUserId();

        usuario = dbHelper.getUsuarioById(userId);

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.textProfileName.setText(usuario.getNombre());
        binding.textProfileLastname.setText(usuario.getApellido());
        binding.textProfileEmail.setText(usuario.getEmail());
        binding.textProfilePassword.setText(usuario.getContraseña());


        View root = binding.getRoot();

        setupEndIconClickListeners();
        return root;
    }
    private void setupEndIconClickListeners() {
        binding.textProfileNameLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog("Editar nombres", binding.textProfileName.getText().toString(), binding.textProfileName);
            }
        });

        binding.textProfileLastnameLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog("Editar apellidos", binding.textProfileLastname.getText().toString(), binding.textProfileLastname);
            }
        });

        binding.textProfileEmailLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog("Editar Correo electrónico", binding.textProfileEmail.getText().toString(), binding.textProfileEmail);

            }
        });

        binding.textProfilePasswordLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordDialog("Editar contraseña");
            }
        });
    }

    private void showEditDialog(String title, String currentText, final TextInputEditText editText) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.dialog_edit_text, null);
        final TextInputEditText dialogEditText = dialogView.findViewById(R.id.edit_text);
        dialogEditText.setText(currentText);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setView(dialogView)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newValue = dialogEditText.getText().toString().trim();

                        if(newValue.isEmpty() || newValue.length() < 3){
                            Toasty.error(getContext(), "El campo no debe estar vacío o con menos de 3 carácteres", Toast.LENGTH_SHORT, true).show();
                        }
                        else {
                            if(title.equals("Editar Correo electrónico") && !isValidEmail(newValue)){
                                Toasty.error(getContext(), "No es un correo válido", Toast.LENGTH_SHORT, true).show();
                                return;
                            }
                            editText.setText(newValue);






                            Toasty.success(getContext(), "Guardado", Toast.LENGTH_SHORT, true).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void showPasswordDialog(String title) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.dialog_edit_password, null);

        final TextInputEditText oldPasswordEditText = dialogView.findViewById(R.id.old_password);
        final TextInputEditText newPasswordEditText = dialogView.findViewById(R.id.new_password);
        final TextInputEditText confirmPasswordEditText = dialogView.findViewById(R.id.confirm_password);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setView(dialogView)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldPassword = oldPasswordEditText.getText().toString();
                        String newPassword = newPasswordEditText.getText().toString();
                        String confirmPassword = confirmPasswordEditText.getText().toString();

                        if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(requireContext(), "Las nuevas contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        } else {
                            // Aquí puedes actualizar la lógica para guardar el valor en la base de datos
                            Toast.makeText(requireContext(), "Aqui va la logica de guardado en base de datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}