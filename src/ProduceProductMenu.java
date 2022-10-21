public class ProduceProductMenu implements ProductMenu{
    @Override
    public void showMenu() {
        System.out.println("You are in the Produce produce menu");
//        showAddButton();
//        showViewButton();
    }

    @Override
    public void showAddButton() {
        System.out.println("Press A to add trading");
    }

    @Override
    public void showViewButton() {
        System.out.println("Press V to view trading");
    }

    @Override
    public void showRadioButton() {

    }

    @Override
    public void showLabels() {

    }

    @Override
    public void showComboxes() {

    }
}
