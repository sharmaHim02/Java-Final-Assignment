/********************************************************************************************************
 * File:  MedicalCertificate.java Course Materials CST 8277
 *
 * @author Teddy Yap
 * 
 */
package acmemedical.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@SuppressWarnings("unused")

/**
 * The persistent class for the medical_certificate database table.
 */
//TODO MC01 - Add the missing annotations.
//TODO MC02 - Do we need a mapped super class?  If so, which one?
@Entity
@Table(name = "medical_certificate")
@NamedQuery(
    name = MedicalCertificate.FIND_BY_ID_QUERY,
    query = "SELECT mc FROM MedicalCertificate mc WHERE mc.id = :param1"
)
public class MedicalCertificate extends PojoBase implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID_QUERY = "MedicalCertificate.findById";

	// TODO MC03 - Add annotations for 1:1 mapping.  What should be the cascade and fetch types?
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    	@JoinColumn(name = "training_id", referencedColumnName = "id", nullable = false)
	private MedicalTraining medicalTraining;

	// TODO MC04 - Add annotations for M:1 mapping.  What should be the cascade and fetch types?
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    	@JoinColumn(name = "physician_id", referencedColumnName = "id", nullable = false)
	private Physician owner;

	// TODO MC05 - Add annotations.
	@Column(name = "signed", nullable = false)
    	@Basic(optional = false)
	private byte signed;

	public MedicalCertificate() {
		super();
	}
	
	public MedicalCertificate(MedicalTraining medicalTraining, Physician owner, byte signed) {
		this();
		this.medicalTraining = medicalTraining;
		this.owner = owner;
		this.signed = signed;
	}

	public MedicalTraining getMedicalTraining() {
		return medicalTraining;
	}

	public void setMedicalTraining(MedicalTraining medicalTraining) {
		this.medicalTraining = medicalTraining;
	}

	public Physician getOwner() {
		return owner;
	}

	public void setOwner(Physician owner) {
		this.owner = owner;
	}

	public byte getSigned() {
		return signed;
	}

	public void setSigned(byte signed) {
		this.signed = signed;
	}

	public void setSigned(boolean signed) {
		this.signed = (byte) (signed ? 0b0001 : 0b0000);
	}
	
	//Inherited hashCode/equals is sufficient for this entity class

}
