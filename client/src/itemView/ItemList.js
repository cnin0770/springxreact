import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavBar from '../component/AppNavBar';
import { Link } from 'react-router-dom';

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {items: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/items')
            .then(response => response.json())
            .then(data => this.setState({items: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/api/item/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedItems = [...this.state.items].filter(i => i.id !== id);
            this.setState({items: updatedItems});
        });
    }

    render() {
        const {items, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const itemList = items.map(
            item => {
                return <tr key={item.id}>
                    <td>{item.id}</td>
                    <td style={{whiteSpace: 'nowrap'}}>{item.name}</td>
                    <td>{item.inventory}</td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/item/" + item.id}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(item.id)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            }
        );

        return (
            <div>
                <AppNavBar />
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/item/new">Add Item</Button>
                    </div>
                    <h3>Items</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">#</th>
                            <th width="20%">Name</th>
                            <th width="20%">Inventory</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {itemList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ItemList;