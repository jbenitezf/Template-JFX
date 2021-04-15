package negocio.model;

public class Libro {
	
	private String titulo;
	private String isbn;
	private Genero genero;
	private String autor;
	private Integer paginas;
	
	public Libro() {
	
		
	}
	
	public Libro(String titulo, String isbn, Genero genero, String autor, Integer paginas) {
		super();
		this.titulo = titulo;
		this.isbn = isbn;
		this.genero = genero;
		this.autor = autor;
		this.paginas = paginas;
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	
	public String toStringfile() {
		return " " + titulo + "," + isbn + "," + genero + "," + autor + ","
				+ paginas+"\n";
	}
	
	public boolean equals (Object o) {
        boolean devu=false;
        Libro a = (Libro)o;
        
        if (this==o) {
        	devu=true;
        }else {
        	if(this.isbn.equalsIgnoreCase(a.isbn)) 
        		devu=true;
        }
        
    	return devu;
        }

	@Override
	public String toString() {
		return " " + titulo + " " + isbn + " " + genero + " " + autor + " "
				+ paginas ;
	}
	
}
