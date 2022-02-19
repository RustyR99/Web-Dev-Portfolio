import java.util.Random;

public class Population
{
    final private int POP_SIZE = 100;//setting my size, as well as establishing total and peak fitness
    private double peakFitness = -1.0;
    private double totalFitness = 0;
    Random Rand = new Random();

    private Candidate[] members = new Candidate[POP_SIZE];
    private double[] probabilities = new double[POP_SIZE];

    public Population(BlackBoxFunction Box)//constructor for first generation
    {
        double thisFitness = 0;

        for(int i = 0; i < members.length; i++)
        {
            members[i] = new Candidate(Box);

            thisFitness = members[i].fitness();

            if(thisFitness > peakFitness)
            {
                peakFitness = thisFitness;
            }
            totalFitness += thisFitness;
        }
    }

    public Population(BlackBoxFunction Box, Candidate[] generation)//constructor for all subsequent generations
    {
        members = generation;

        double thisFitness = 0;

        for(int i = 0; i < members.length; i++)
        {
            thisFitness = members[i].fitness();

            if(thisFitness > peakFitness)
            {
                peakFitness = thisFitness;
            }
            totalFitness += thisFitness;
        }
    }

    public Candidate Roulette()//implementation of roulette selection which is admittedly janky
    {
        double peakMember = Rand.nextDouble();
        double index = 0;

        for(int i = 0; i < members.length; i++)
        {
            if(i == 0)
            {
                if(peakMember <= probabilities[i])
                {
                    return members[i];
                }
            }
            else
            {
                if(peakMember >= (index) && peakMember <= (probabilities[i] + index))
                {
                    return members[i];
                }
            }
            index += probabilities[i];
        }
        return null;
    }

    public Candidate[] Generation()//creating and filling a new generation with offspring of current gen
    {
        Candidate[] newGen = new Candidate[POP_SIZE];
        Candidate[] progeny = new Candidate[2];

        Candidate parent1;
        Candidate parent2;

        for(int i = 0; i < probabilities.length; i++)
        {
            probabilities[i] = members[i].getFitness()/totalFitness;
        }

        for(int i = 0; i < newGen.length/2; i++)
        {
            parent1 = Roulette();
            parent2 = Roulette();

            progeny = parent1.Crossover(parent2);

            newGen[i] = progeny[0];
            newGen[i + 50] = progeny[1]; 
        }
        return newGen;
    }
//two methods for info retrieval
    public double getAvgFitness()
    {
        return totalFitness/100;
    }

    public double getPeakFitness()
    {
        return peakFitness;
    }
}