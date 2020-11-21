package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportsWithPartsDto {

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlElement
    private PartsRootDto parts;


    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    public CarExportsWithPartsDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartsRootDto getParts() {
        return parts;
    }

    public void setParts(PartsRootDto parts) {
        this.parts = parts;
    }
}
