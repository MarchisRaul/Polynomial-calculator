package MVCmodel;

public class Monom {
	private float coeficient;
	private int putere;

	public Monom(float coeficient, int putere) {
		super();
		this.coeficient = coeficient;
		this.putere = putere;
	}

	public float getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(int coeficient) {
		this.coeficient = coeficient;
	}

	public int getPutere() {
		return putere;
	}

	public void setPutere(int putere) {
		this.putere = putere;
	}

}
