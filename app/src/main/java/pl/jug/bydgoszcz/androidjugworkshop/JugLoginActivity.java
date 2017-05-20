package pl.jug.bydgoszcz.androidjugworkshop;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pl.jug.bydgoszcz.androidjugworkshop.databinding.ActivityJugLoginBinding;

public class JugLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityJugLoginBinding binding = DataBindingUtil.
                setContentView(this, R.layout.activity_jug_login);

        binding.jugLoginButton.setOnClickListener(view -> {
            String loginValue = binding.jugLoginButton.getText().toString();
            String passwordValue = binding.jugLoginButton.getText().toString();

            String fullCreds = loginValue + ", " + passwordValue;

            Toast.makeText(JugLoginActivity.this, fullCreds, Toast.LENGTH_SHORT).show();

            binding.setUser(fullCreds);
        });
    }
}
