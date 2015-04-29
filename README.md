# hashcode-equals

[![Build Status](https://travis-ci.org/jediq/hashcode-equals.svg?branch=master)](https://travis-ci.org/jediq/hashcode-equals)

Java 8 implementation of hashCode() and equals() builder pattern.

## Usage : 

```
private LambdaHashCodeEquals <Thing> lhce = new LambdaHashCodeEquals <>(Thing.class)
        .with(t -> t.int1)
        .with(t -> t.int2)
        .with(t -> t.string1)
        .with(t -> t.string2);
        

@Override
public int hashCode() {
    return lhce.hashCodeFor(this);
}

@Override
public boolean equals(Object o) {
    return lhce.areEqual(this, o);
}
```
