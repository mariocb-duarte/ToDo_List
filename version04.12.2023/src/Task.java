public class Task {
    String titulo;
    String descricao;
    String categoria;
    String status;



    public Task(String titulo, String descricao, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.status = "TO-DO";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(){
        if (this.status.equals("TO-DO")){
            this.status = "DONE";
        }else {
            this.status = "TO-DO";
        }
    }



}
