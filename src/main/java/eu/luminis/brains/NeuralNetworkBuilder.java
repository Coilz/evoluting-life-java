package eu.luminis.brains;

import eu.luminis.genetics.*;
import java.util.List;

public class NeuralNetworkBuilder {
    private NeuralNetworkGene neuralNetworkGene;

    private NeuralNetworkBuilder() {
    }

    public static NeuralNetworkBuilder create() {
        return new NeuralNetworkBuilder();
    }

    public NeuralNetworkBuilder withNeuralNetworkGene(NeuralNetworkGene neuralNetworkGene) {
        this.neuralNetworkGene = neuralNetworkGene;

        return this;
    }

    public IBrain build() {
        List<LayerGene> layerGenes = this.neuralNetworkGene.getLayers();
        Layer[] layers = new Layer[layerGenes.size()];

        for (int i=0; i<layers.length-1; i++) {
            LayerGene layerGene = layerGenes.get(i);
            Layer layer = LayerBuilder.create()
                .withLayerGene(layerGene)
                .build();
            layers[i] = layer;
        }

        Layer outputLayer = LayerBuilder.create()
            .withLayerGene(layerGenes.get(layers.length-1))
            .buildAsOutput();
        layers[layers.length-1] = outputLayer;

        return new NeuralNetwork(layers);
    }
}