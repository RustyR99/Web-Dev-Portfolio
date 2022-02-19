import javax.swing.text.html.BlockView;
//the main loop plus some easter eggs cause i got bored
public class GA 
{
    public static void main(String[] args)
    {
        Population presentGen;
        Population nextGen;
        BlackBoxFunction Box = new BlackBoxFunction();

        double globalBest = -1;
        presentGen = new Population(Box);
        System.out.printf("Generation Num: %d || Best Fitness: %f || Average Fitness: %f || Global Best: %f%n%n",0, presentGen.getPeakFitness(), presentGen.getAvgFitness(), globalBest);

        for(int i = 1; i <= 100000; i++)
        {
            nextGen = new Population(Box, presentGen.Generation());
            presentGen = nextGen;

            if(i==320)
            {
                System.out.println("THE CIA KILLED JFK");
            }

            if(i==5600)
            {
                System.out.println("THE CAKE IS A LIE");
            }

            if(presentGen.getPeakFitness() > globalBest)
            {
                globalBest = presentGen.getPeakFitness();
            }
            
            System.out.printf("Generation Num: %d || Best Fitness: %f || Average Fitness: %f || Global Best: %f%n%n",i, presentGen.getPeakFitness(), presentGen.getAvgFitness(), globalBest);
        }
    }
}
