import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    ArrayList<Task> taskList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String leitura = "";

    public static void main(String[] args) {
        TaskManager exemplo = new TaskManager();
    }

    public TaskManager() {
        initialize();
    }


    public void initialize() {
        System.out.println("\nBem vindo(a) ao TaskManager.\n");
        System.out.println("TaskManager é uma alplicação para gerenciar tarefas (to-do list).");
        System.out.println("Use-o para criar novas tarefas e gerenciar tarefas existentes.\n");

        output("Você pode me controlar usando os seguintes comandos:\n");

        while (true) {

            System.out.println("1 - NewTask");
            System.out.println("2 - MyTasks");
            System.out.println("3 - EditTask");
            System.out.println("4 - Exit\n");

            input();

            if (getLeitura().equals("4")) {
                break;
            }
            switch (getLeitura()) {

                case "1":
                    newTask();
                    break;
                case "2":
                    mytasks();
                    break;
                case "3":
                    editTask();
                    break;

                default:
                    output("Ops..Você não digitou um comando ou o comando é inválido.!");
                    break;
            }
            output("Escolha um comando:\n");
        }
    }

    public void input() {
        System.out.print("Digite aqui: ");
        this.leitura = scanner.nextLine();
    }

    public void output(String value) {
        System.out.println("TaskManager: " + value);
    }

    public String getLeitura() {
        return leitura;
    }

    public Boolean isTask(String value) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTitulo().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public Integer indexTask(String value) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTitulo().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return null;
    }

    public void newTask() {
        String memoriaTitulo;
        int memoriaIndexTitulo = 0;
        System.out.println("NewTask__________________________________________________________________________________");
        while (true) {
            output("Qual o título da sua Task? ");
            input();
            if (getLeitura().isBlank()) {
                output("Opss..o título deve ser preenchido. Caso deseje voltar para o TaskManager, digite /voltar.");
            } else if (getLeitura().equals("/voltar")) {
                return;
            } else {
                memoriaTitulo = getLeitura();

                if (taskList.isEmpty()) {
                    taskList.add(new Task(memoriaTitulo, "", ""));
                    output("Sua primeira TASK foi criada com sucesso.");
                } else if (isTask(memoriaTitulo)) {
                    output("Essa Task já existe. Caso deseje voltar para o TaskManager, digite /voltar.");
                    continue;
                } else {
                    taskList.add(new Task(memoriaTitulo, "", ""));
                    memoriaIndexTitulo = indexTask(memoriaTitulo);
                    output("Muito bem! Task criada com sucesso!");
                }
                output("Agora preencha a descrição da sua nova Task.");
                input();
                if (getLeitura().isBlank()) {
                    output("Você não descreveu sua Task.");
                } else {
                    taskList.get(memoriaIndexTitulo).setDescricao(getLeitura());
                    output("Boa! Sua Task agora contém descrição!");
                }

                output("Agora digite a categoria da sua nova Task.");
                input();

                if (getLeitura().isBlank()) {
                    output("Você não adicionou categoria a sua Task.");
                    output("Para ver suas Tasks, acesse MyTasks");
                    break;

                } else {
                    taskList.get(memoriaIndexTitulo).setCategoria(getLeitura());
                    output("Ótimo! Sua nova Task agora pertence a uma categoria.");
                    output("Para ver suas Tasks, acesse MyTasks. Para editar, acesse EditTask.");
                    break;
                }
            }
        }
    }

    public void mytasks() {
        int contTodo = 0;
        int contDone = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getStatus().equalsIgnoreCase("TO-DO")) {
                contTodo += 1;
            } else if (taskList.get(i).getStatus().equalsIgnoreCase("DONE")) {
                contDone += 1;
            }
        }
        output("My TASKS__________________________________________________");
        if (taskList.isEmpty()) {
            System.out.println("             " + "Você não tem Task na sua Lista ainda.");
            System.out.println("             " + "__________________________________________________________");
        } else if (taskList.size() == 1) {
            System.out.println("             " + "Você tem " + "[" + taskList.size() + " Task na sua Lista" + "]");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getStatus().equalsIgnoreCase("TO-DO")) {
                    System.out.println("             " + "TO-DO_____________________________________________________");
                    System.out.println("             " + "Você tem " + "[" + taskList.size() + " Task TO-DO" + "]");
                    System.out.println("             " + "__________________________________________________________");
                    System.out.println("             " + "Titulo: " + taskList.get(i).getTitulo());
                    System.out.println("             " + "Categoria: " + taskList.get(i).getCategoria());
                    System.out.println("             " + "Descrição: " + taskList.get(i).getDescricao());
                    System.out.println("             " + "__________________________________________________________");
                } else {
                    System.out.println("             " + "DONE_____________________________________________________");
                    System.out.println("             " + "Você tem " + "[" + taskList.size() + " Task DONE" + "]");
                    System.out.println("             " + "__________________________________________________________");
                    System.out.println("             " + "Titulo: " + taskList.get(i).getTitulo());
                    System.out.println("             " + "Categoria: " + taskList.get(i).getCategoria());
                    System.out.println("             " + "Descrição: " + taskList.get(i).getDescricao());
                    System.out.println("             " + "__________________________________________________________");
                }

            }
        } else {
            System.out.println("             " + "Você tem " + "[" + taskList.size() + " Task na sua Lista" + "]");
            System.out.println("             " + "TO-DO_____________________________________________________");
            if (contTodo == 0) {
                System.out.println("             " + "Você não tem Task TO-DO");
            } else if (contTodo == 1) {
                System.out.println("             " + "Você tem " + "[" + contTodo + " Task TO-DO" + "]");
            } else {
                System.out.println("             " + "Você tem " + "[" + contTodo + " Tasks TO-DO" + "]");
            }
            System.out.println("             " + "__________________________________________________________");

            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getStatus().equalsIgnoreCase("TO-DO")) {
                    System.out.println("             " + "Titulo: " + taskList.get(i).getTitulo());
                    System.out.println("             " + "Categoria: " + taskList.get(i).getCategoria());
                    System.out.println("             " + "Descrição: " + taskList.get(i).getDescricao());
                    System.out.println("             " + "__________________________________________________________");
                }
            }

            System.out.println("             " + "DONE______________________________________________________");
            if (contDone == 0) {
                System.out.println("             " + "Você não tem Task DONE");
            } else if (contDone == 1) {
                System.out.println("             " + "Você tem " + "[" + contDone + " Task DONE" + "]");
            } else {
                System.out.println("             " + "Você tem " + "[" + contDone + " Task DONE" + "]");
            }
            System.out.println("             " + "__________________________________________________________");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getStatus().equalsIgnoreCase("DONE")) {
                    System.out.println("             " + "Titulo: " + taskList.get(i).getTitulo());
                    System.out.println("             " + "Categoria: " + taskList.get(i).getCategoria());
                    System.out.println("             " + "Descrição: " + taskList.get(i).getDescricao());
                    System.out.println("             " + "__________________________________________________________");
                }
            }
        }
    }

    public void editTask() {
        if (taskList.isEmpty()) {
            output("Opss..Você ainda não tem Task.");
            return;
        }

        while (true) {

            output("Digite o titulo da Task que você deseja editar:");
            input();
            if (getLeitura().isBlank()) {
                output("Para editar uma Task você precisa informar o titulo. Para voltar ao TaskManager digite /voltar");
            } else if (getLeitura().equals("/voltar")) {
                return;
            } else {
                if (!isTask(getLeitura())) {
                    output("Essa Task não foi localizada ou ainda não foi criada. Para voltar ao TaskManager digite /voltar");
                } else {
                    String memoriaTitulo = getLeitura();
                    int memoriaIndexTitulo = indexTask(getLeitura());

                    output("O que você gostaria de editar na Task: " + memoriaTitulo);

                    System.out.println("1 - Título");
                    System.out.println("2 - Descrição");
                    System.out.println("3 - Categoria");
                    System.out.println("4 - Status\n");

                    input();

                    if (getLeitura().equals("1")) {
                        output("Qual o novo título para essa Task? ");
                        input();
                        if (!getLeitura().isBlank()) {
                            taskList.get(memoriaIndexTitulo).setTitulo(getLeitura());
                            output("Titulo alterado de " + memoriaTitulo + " para " + getLeitura());
                            return;
                        } else {
                            output("Titulo não pode ser em branco.");
                            return;
                        }

                    } else if (getLeitura().equals("2")) {
                        output("Qual a nova descrição para essa Task? ");
                        input();
                        taskList.get(memoriaIndexTitulo).setDescricao(getLeitura());
                        output("Descrição alterada com sucesso.");
                        return;

                    } else if (getLeitura().equals("3")) {
                        output("Qual a nova categoria para essa Task? ");
                        input();
                        taskList.get(memoriaIndexTitulo).setCategoria(getLeitura());
                        output("Categoria alterada com sucesso.");
                        return;

                    } else if (getLeitura().equals("4")) {
                        taskList.get(memoriaIndexTitulo).setStatus();
                        output("Status alterado com sucesso.");
                        return;

                    } else {
                        output("Ops..Você não digitou um comando ou o comando é inválido.!");
                        return;
                    }
                }
            }
        }
    }
}

