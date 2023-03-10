//-----------------------------------------------------
// Title: Vertex Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 2
// Description: This class is for the creation of vertex.
//-----------------------------------------------------

import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
	private String vertexElement;

	public Vertex(String vertexElement) {
		super();
		this.vertexElement = vertexElement;
	}

	public String getVertexElement() {
		return vertexElement;
	}

	public void setVertexElement(String vertexElement) {
		this.vertexElement = vertexElement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertexElement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return vertexElement.equals(other.getVertexElement());
	}

	@Override
	public int compareTo(Vertex o) {
		return vertexElement.compareTo(o.getVertexElement());
	}
	
	
	
}

