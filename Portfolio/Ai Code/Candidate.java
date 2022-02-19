import java.util.Random;

public class Candidate
{
    Random Rand = new Random();
    final private double Pm = 0.001;//establishing fields for pm and pc and other relevant info
    final private double Pc = 0.5;
    private double fitness;
    private boolean[] Strand;
    BlackBoxFunction Box;


    public Candidate(BlackBoxFunction newBox)//initial constrcutor
    {
        Box = newBox;
        Strand = new boolean[Box.getLength()];

        for(int i = 0; i < Strand.length; i++)
        {
            Strand[i] = Rand.nextBoolean();
        }
    }

    public Candidate(BlackBoxFunction newBox, boolean[] progeny)//constructor for all subsequent candidates
    {
        Box = newBox;
        Strand = new boolean[Box.getLength()];
        for(int i = 0; i < Strand.length; i++)
        {
            Strand[i] = progeny[i];
        }

    }

    public double fitness()//computes and returns the fitness
    {
        fitness = Box.function(Strand);
        return fitness;
    }

    public double getFitness()//kind of redundant but its easier not to have to calculate each time so i left it in
    {
        return fitness;
    }

    public boolean[] getStrand()//returns the bit string of the candidate
    {
        return Strand;
    }

    public boolean getStrand(int i)//returns the bit at a given locus
    {
        return Strand[i];
    }

    public Candidate[] Crossover(Candidate parent)
    {
        Candidate[] progeny = new Candidate[2];//array of candidate children
        boolean[] prog1 = new boolean[Strand.length]; //the first progeny starts with dna strand of this parent
        boolean[] prog2 = new boolean[Strand.length];

        double cross = Rand.nextDouble();

        for(int i = 0; i < Strand.length; i++)
        {
            prog1[i] = Strand[i];
            prog2[i] = parent.getStrand(i);//filling the progenies
        }

        if(cross < Pc)
        {
            int point = Rand.nextInt();
            for(int i = 0; i < Strand.length; i++)
            {
                if(i >= point)
                {
                    prog1[i] = parent.getStrand(i);//swapping the progenies at a given cross point onward
                    prog2[i] = Strand[i];
                }
            }
        }
        prog1 = mutation(prog1);
        prog2 = mutation(prog2);

        progeny[0]  = new Candidate(Box, prog1);
        progeny[1] = new Candidate(Box, prog2);

        return progeny;
    }

    public boolean[] mutation(boolean[] progeny)//just inverts the bit at a given locus
    {
        for(int i = 0; i < progeny.length; i++)
        {
            double mutate = Rand.nextDouble();
            if(mutate < Pm)
            {
                progeny[i] = !progeny[i];
            }
        }
        return progeny;
    }
}