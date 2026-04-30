package it.home.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "opere")
public class Opera {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 30)
	private String titolo;

	@Column(length = 255)
	private String descrizione;

	@Column(name = "img_path", length = 255)
	private String imgPath;

	@Column
	private int anno;

	// variabile che serve per definire quali sono le opere
	@Column(name = "is_works")
	private boolean work;
	
	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, descrizione, id, imgPath, titolo, work);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opera other = (Opera) obj;
		return anno == other.anno && Objects.equals(descrizione, other.descrizione) && id == other.id
				&& Objects.equals(imgPath, other.imgPath) && Objects.equals(titolo, other.titolo) && work == other.work;
	}

	@Override
	public String toString() {
		return "Opera [id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + ", imgPath=" + imgPath
				+ ", anno=" + anno + ", work=" + work + "]";
	}
	
	
}
