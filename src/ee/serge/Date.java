package ee.serge;

public class Date {

    private int[] hours;
    private int monthIndex;
    private int year;

    public enum Month {
        JANUARY(1, 31), FEBRUARY(2, 30), MARCH(3, 31), OCTOBER(10, 31);

        private int index;
        private int lengthInDays;

        private Month (int index, int lengthInDays) {
            this.index = index;
            this.lengthInDays = lengthInDays;
        }

        public int getIndex() {
            return index;
        }

        public int getLengthInDays() {
            return lengthInDays;
        }
    }

    public Date(int monthIndex, int year) {
        this.monthIndex = monthIndex;
        this.year = year;
    }

    public int getMonthIndex() {
        return monthIndex;
    }

    public int getYear() {
        return year;
    }

    public int[] getHours() {
        return hours;
    }

    public void setHours(int[] hours) {
        this.hours = hours;
    }

    public void setHour(int index, int consumption) {
        if (hours == null) hours = new int[24];
        hours[index] = consumption;
    }

}
