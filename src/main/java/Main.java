import adapter.ConsoleAdapter;
import usecase.FuncionarioService;

public class Main {

    public static void main(String[] args) {

        FuncionarioService funcionarioService = new FuncionarioService();
        ConsoleAdapter consoleAdapter = new ConsoleAdapter(funcionarioService);
        consoleAdapter.iniciar();
    }
};
