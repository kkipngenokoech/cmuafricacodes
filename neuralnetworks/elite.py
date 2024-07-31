import numpy as np
np.random.seed(123)

# this is the core model
from keras.models import Sequential
# these are the core layers
from keras.layers import Dense, Dropout, Activation, Flatten
# time to import the CNN layers
from keras.layers import Convolution2D, MaxPooling2D
# import the utilities
# from keras.utils import np_utils

# Load pre-shuffled MNIST data into train and test sets 
from keras.datasets import mnist
from matplotlib import pyplot as plt

(x_train, y_train), (x_test, y_test) = mnist.load_data()

print(x_train.shape)
plt.imshow(x_train[0])