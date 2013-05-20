package ee.serge;

public class MonthlyConsumption {
    private int dayConsumption;
    private int nightConsumption;

    public MonthlyConsumption(int dayConsumption, int nightConsumption) {
        this.dayConsumption = dayConsumption;
        this.nightConsumption = nightConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthlyConsumption that = (MonthlyConsumption) o;

        if (dayConsumption != that.dayConsumption) return false;
        if (nightConsumption != that.nightConsumption) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dayConsumption;
        result = 31 * result + nightConsumption;
        return result;
    }

    @Override
    public String toString() {
        return "MonthlyConsumption{" +
                "dayConsumption=" + dayConsumption +
                ", nightConsumption=" + nightConsumption +
                '}';
    }
}
