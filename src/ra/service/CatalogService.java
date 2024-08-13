package ra.service;

import ra.model.Catalog;

import java.util.*;

public class CatalogService implements IGenericService<Catalog, Integer>{
    private List<Catalog> catalogList = new ArrayList<>();
    @Override
    public List<Catalog> getAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        catalogList.add(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        Optional<Catalog> catalog= catalogList.stream().filter(c->c.getCatalogId()==id).findFirst();
        return catalog.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        catalogList.removeIf(c->c.getCatalogId()==id);
    }


}