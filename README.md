# hashcode-equals

Java 8 implementation of hashCode() and equals() builder pattern.

## Usage : 

```
private LambdaEqualsHashCode <Thing> lehc = new LambdaEqualsHashCode <>(Thing.class)
        .with(t -> t.int1)
        .with(t -> t.int2)
        .with(t -> t.string1)
        .with(t -> t.string2);
        

@Override
public int hashCode() {
    return lehc.hashCodeFor(this);
}

@Override
public boolean equals(Object o) {
    return lehc.areEqual(this, o);
}
```
